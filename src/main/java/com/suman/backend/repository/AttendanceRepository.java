package com.suman.backend.repository;
import com.suman.backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    boolean existsByStudentEmailAndClassIdAndDate(
            String studentEmail,
            String classId,
            LocalDate date
    );
    boolean existsByStudentEmailAndSessionToken(
            String studentEmail,
            String sessionToken
    );

    List<Attendance> findByClassId(String classId);
}
