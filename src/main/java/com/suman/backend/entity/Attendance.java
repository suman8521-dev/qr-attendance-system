package com.suman.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"studentEmail", "sessionToken"})
        }
)
public class Attendance {

    @Id
    private String id;

    private String studentEmail;

    private String classId;

    private String sessionToken;

    private LocalDate date;

    private LocalTime time;

    private String status;
}