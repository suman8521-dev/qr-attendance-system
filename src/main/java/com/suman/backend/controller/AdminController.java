package com.suman.backend.controller;

import com.suman.backend.entity.Attendance;
import com.suman.backend.entity.ClassEntity;
import com.suman.backend.entity.QRSession;
import com.suman.backend.entity.User;
import com.suman.backend.requests.CreateClassRequest;
import com.suman.backend.service.AttendanceService;
import com.suman.backend.service.ClassService;
import com.suman.backend.service.QRService;
import com.suman.backend.service.UserService;
import com.suman.backend.utility.QRUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ClassService classService;
    private final QRService qrService;
    private final AttendanceService attendanceService;

    @GetMapping("/generate/{classId}")
    public ResponseEntity<?> generateQR(@PathVariable String classId) {

        try {
            QRSession session = qrService.generateSession(classId);

            String url = "http://localhost:8089/api/v1/attendance/scan?token="
                    + session.getToken();

            byte[] qr = QRUtil.generateQR(url);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qr);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body("QR generation failed: " + e.getMessage());
        }
    }

    @GetMapping("/attendance/{classId}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable String classId){
        return ResponseEntity.ok(
                attendanceService.getAttendanceByClass(classId)
        );
    }

    @PostMapping("/create/class")
    public ResponseEntity<ClassEntity> createClass(@RequestBody CreateClassRequest req) {
        return ResponseEntity.ok(
                classService.createClass(req));
    }

    @GetMapping("")
    public ResponseEntity<String> adminGreeting() {
        return ResponseEntity.ok("Hello admin u are reading this message from a protected endpoint. Only admins can access this endpoint.");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/locked-users")
    public ResponseEntity<List<User>> getLockedUsers() {
        return ResponseEntity.ok(userService.getLockedUsers());
    }

    @GetMapping("/unlocked-users")
    public ResponseEntity<List<User>> getUnlockedUsers() {
        return ResponseEntity.ok(userService.getUnlockedUsers());
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/lock-user/{email}")
    public ResponseEntity<String> enableUser(@PathVariable String email) {
        userService.lockUser(email);
        return ResponseEntity.ok("User locked successfully");
    }

    @PostMapping("/unlock-user/{email}")
    public ResponseEntity<String> disableUser(@PathVariable String email) {
        userService.unlockUser(email);
        return ResponseEntity.ok("User unlocked successfully");
    }

}
