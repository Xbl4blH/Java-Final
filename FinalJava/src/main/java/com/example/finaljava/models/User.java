package com.example.finaljava.models;

import lombok.Data;

@Data
public class User {
    private int id;
    private String login;
    private String pasword;
    private String role;
    private Boolean status;
}
