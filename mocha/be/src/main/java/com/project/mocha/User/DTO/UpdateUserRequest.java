package com.project.mocha.User.DTO;

public record UpdateUserRequest(
        String id,
        String pwd,
        boolean isAdult
){}
