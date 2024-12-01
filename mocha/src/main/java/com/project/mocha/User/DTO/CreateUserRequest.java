package com.project.mocha.User.DTO;

public record CreateUserRequest(
        int id,
        String uid,
        String pwd
){}
