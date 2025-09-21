package com.project.mocha.User.DTO;

public record CreateUserRequest(
        String id,
        String pwd,
        boolean isAdult
){}
