package com.project.mocha.User.Controller;

import com.project.mocha.User.DTO.CreateUserResponse;
import com.project.mocha.User.DTO.CreateUserRequest;
import com.project.mocha.User.DTO.ReadUserResponse;
import com.project.mocha.User.DTO.UpdateUserRequest;
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

    /*
    user id는 url에 안 들어가는 게 보안에 좋다고 해서 일단 뺐음
     */

    @PostMapping
    @Operation(summary="create user")
    public ResponseEntity<CreateUserResponse> createUser(
            @Parameter(required=true, description="user create request")
            @RequestBody CreateUserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping
    @Operation(summary="get user by id")
    public ResponseEntity<ReadUserResponse> getUser(
            @Parameter(required=true, description="user id") int id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping
    @Operation(summary="update user by id")
    public ResponseEntity<CreateUserResponse> updateUser(
            @Parameter(required=true, description="user id") int id,
            @Parameter(required=true, description="user update request")
            @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping
    @Operation(summary="delete user by id")
    public ResponseEntity<ReadUserResponse> deleteUser(
            @Parameter(required=true, description="user id") int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
