package com.project.mocha.User.DTO;

public record UpdateUserRequest(
        int userId,
        String uid,
        String pwd,
        boolean isAdult
){}
