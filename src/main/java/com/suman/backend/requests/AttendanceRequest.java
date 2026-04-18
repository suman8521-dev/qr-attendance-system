package com.suman.backend.requests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequest {
    private String token;
    private String studentEmail;
}