package com.project.mocha.User.Service;

import com.project.mocha.User.DTO.CreateUserRequest;
import com.project.mocha.User.DTO.CreateUserResponse;
import com.project.mocha.User.DTO.ReadUserResponse;
import com.project.mocha.User.DTO.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    public CreateUserResponse createUser(CreateUserRequest request){
        return null;
    }

    public ReadUserResponse getUser(int id){
        return null;
    }

    // Create와 Update 때 주고받는 DTO가 비슷할 거라고 판단해 재사용함
    public CreateUserResponse updateUser(int id, UpdateUserRequest request){
        return null;
    }

    public void deleteUser(int id){}
}
