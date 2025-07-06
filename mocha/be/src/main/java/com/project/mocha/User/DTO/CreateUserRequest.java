package com.project.mocha.User.DTO;

public record CreateUserRequest(
        String uid,
        String pwd,
        boolean isAdult
){}
