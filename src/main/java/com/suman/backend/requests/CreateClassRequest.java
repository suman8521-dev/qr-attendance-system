package com.suman.backend.requests;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClassRequest {
    private String className;
    private String description;
    private String email;
}