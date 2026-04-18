package com.suman.backend.controller;
import com.suman.backend.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final AttendanceService attendanceService;
    @GetMapping("/user")
    public ResponseEntity<String> userGreeting() {
        return ResponseEntity.ok("Hello user u are reading this message from a protected endpoint. Only users can access this endpoint.");
    }

    @GetMapping("/attendance/scan")
    public ResponseEntity<String> scanAndMark(@RequestParam String token,
                                              Principal principal) {
        String email = principal.getName(); // login user auto detect

        return ResponseEntity.ok(
                attendanceService.markAttendance(token, email)
        );
    }
}