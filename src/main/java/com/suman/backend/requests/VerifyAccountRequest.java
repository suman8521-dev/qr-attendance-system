package com.suman.backend.requests;
import lombok.Data;

@Data
public class VerifyAccountRequest {
    private String email;
    private String code;
}
