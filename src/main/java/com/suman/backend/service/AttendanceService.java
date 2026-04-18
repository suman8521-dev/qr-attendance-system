package com.suman.backend.service;
import com.suman.backend.entity.Attendance;
import com.suman.backend.entity.QRSession;
import com.suman.backend.repository.AttendanceRepository;
import com.suman.backend.repository.QRSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {

    private final QRSessionRepository qrRepo;
    private final AttendanceRepository attendanceRepo;
    private final LiveAttendanceService liveService;



    public String markAttendance(String token, String studentEmail) {

        QRSession session = qrRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid QR"));

        if (session.getExpiresAt().isBefore(LocalDateTime.now()) || !session.isActive()) {
            return "QR expired";
        }

        if (attendanceRepo.existsByStudentEmailAndSessionToken(studentEmail, token)) {
            return "Attendance already marked for this session";
        }
        //save attendance
        Attendance a = new Attendance();
        a.setId(UUID.randomUUID().toString());
        a.setStudentEmail(studentEmail);
        a.setSessionToken(token);
        a.setClassId(session.getClassId());
        a.setDate(LocalDate.now());
        a.setTime(LocalTime.now());
        a.setStatus("PRESENT");

        attendanceRepo.save(a);
        // REAL TIME PUSH
        liveService.sendUpdate(a);
        return "Attendance marked successfully";
    }

    public List<Attendance> getAttendanceByClass(String classId){
        return attendanceRepo.findByClassId(classId);
    }


}
