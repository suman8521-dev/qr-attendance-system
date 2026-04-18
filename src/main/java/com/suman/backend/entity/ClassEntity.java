package com.suman.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class ClassEntity {

    @Id
    private String id;

    private String className;

    private String description;

    private String admin_id; // admin id
}