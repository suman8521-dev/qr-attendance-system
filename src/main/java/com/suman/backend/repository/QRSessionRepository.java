package com.suman.backend.repository;
import com.suman.backend.entity.QRSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QRSessionRepository extends JpaRepository<QRSession, String> {
    Optional<QRSession> findByToken(String token);

    Optional<QRSession> findByClassIdAndActiveTrue(String classId);
}