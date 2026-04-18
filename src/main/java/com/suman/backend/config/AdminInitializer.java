package com.suman.backend.config;
import com.suman.backend.entity.User;
import com.suman.backend.repository.UserRepository;
import com.suman.backend.service.AttendanceService;
import com.suman.backend.service.ClassService;
import com.suman.backend.service.QRService;
import com.suman.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;
import static com.suman.backend.entity.Role.ROLE_ADMIN;

/**
 * Initializes the admin user if it does not already exist.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ClassService classService;
    private final QRService qrService;
    private final AttendanceService attendanceService;
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    private User buildAdminUser() {
        return User.builder()
                .id(UUID.randomUUID().toString())
            .firstName("admin")
            .lastName("admin")
            .email(adminUsername)
            .password(adminPassword)
            .confirmPassword(adminPassword)
            .role(ROLE_ADMIN)
            .enabled(true)
            .accountNonLocked(true)
            .build();
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.existsByEmail(adminUsername)) {
            log.info("Admin user already exists.\n username: {}", adminUsername);
            return;
        }

        User admin = buildAdminUser();
        admin.setPassword(passwordEncoder.encode(adminPassword));
        userRepository.save(admin);

        log.info("Admin user created successfully. username: {}", adminUsername);
    }


}
