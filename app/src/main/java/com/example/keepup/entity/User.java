package com.example.keepup.entity;

import lombok.Data;

@Data
public abstract class User {

    private int userId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String position;
}
