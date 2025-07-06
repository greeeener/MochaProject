package com.project.mocha.User.Controller;

import com.project.mocha.User.DTO.*;
import com.project.mocha.User.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mc/user")
@Tag(name="user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary="create user")
    public int createUser(
            @Parameter(required=true, description="create user request")
            @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @PostMapping("/get")
    @Operation(summary="get user by id")
    public ResponseEntity<ReadUserResponse> getUser(
            @Parameter(required=true, description="read user request")
            @RequestBody ReadUserRequest request){
        return ResponseEntity.ok(userService.getUser(request));
    }

    @PutMapping
    @Operation(summary="update user by id")
    public int updateUser(
            @Parameter(required=true, description="update user request")
            @RequestBody UpdateUserRequest request){
        return userService.updateUser(request);
    }

    @DeleteMapping
    @Operation(summary="delete user by id")
    public ResponseEntity<ReadUserResponse> deleteUser(
            @Parameter(required=true, description="delete user request")
            @RequestBody ReadUserRequest request){
        userService.deleteUser(request);
        return ResponseEntity.noContent().build();
    }
}
