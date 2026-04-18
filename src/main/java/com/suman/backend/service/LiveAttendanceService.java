package com.suman.backend.service;
import com.suman.backend.entity.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveAttendanceService {

    private final SimpMessagingTemplate template;

    public void sendUpdate(Attendance attendance) {
        template.convertAndSend("/topic/attendance", attendance);
    }
}
