package com.suman.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class QRSession {

    @Id
    private String id;

    private String classId;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private boolean active = true;
}