package com.project.mocha.User.DTO;

public record ReadUserResponse(
        int userId,
        String uid,
        boolean isAdult
){}
