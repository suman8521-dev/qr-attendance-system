package com.suman.backend.service;
import com.suman.backend.entity.ClassEntity;
import com.suman.backend.entity.User;
import com.suman.backend.repository.ClassRepository;
import com.suman.backend.repository.UserRepository;
import com.suman.backend.requests.CreateClassRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final UserRepository userRepository;

    public ClassEntity createClass(CreateClassRequest request) {

        User admin = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        ClassEntity c = new ClassEntity();
        c.setId(UUID.randomUUID().toString());
        c.setClassName(request.getClassName());
        c.setDescription(request.getDescription());
        c.setAdmin_id(admin.getId());
        return classRepository.save(c);
    }

}
