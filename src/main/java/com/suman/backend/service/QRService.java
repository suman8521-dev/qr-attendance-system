package com.suman.backend.service;
import com.suman.backend.entity.QRSession;
import com.suman.backend.repository.QRSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QRService {

    private final QRSessionRepository repo;

    public QRSession generateSession(String classId) {

        Optional<QRSession> existingSession =
                repo.findByClassIdAndActiveTrue(classId);

        if (existingSession.isPresent()) {
            return existingSession.get();
        }

        QRSession session = new QRSession();
        session.setId(UUID.randomUUID().toString());
        session.setClassId(classId);
        session.setToken(UUID.randomUUID().toString());
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusSeconds(6000));
        session.setActive(true);
        return repo.save(session);
    }

    }
