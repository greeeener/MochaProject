package com.project.mocha.User.Service;

import com.project.mocha.User.DTO.*;
import com.project.mocha.User.Entity.User;
import com.project.mocha.User.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Setter
@RequiredArgsConstructor
@Service
public class UserService {

    /*
    나중에 Session 사용하게 되면 DTO 구조랑 로직 바꿔야할 듯
     */

    private final UserRepository userRepository;

    @Transactional
    public int createUser(CreateUserRequest request){
        User user = User.builder()
                .uid(request.uid())
                .pwd(request.pwd())
                .isAdult(request.isAdult())
                .build();
        return userRepository.save(user).getUserId();
    }

    public ReadUserResponse getUser(ReadUserRequest request){
        Optional<User> result = userRepository.findById(request.userId());
        if (result.isPresent()){
            User user = result.get();
            return new ReadUserResponse(user.getUserId(), user.getUid(), user.isAdult());
        }
        return null;
    }

    @Transactional
    public int updateUser(UpdateUserRequest request){
        User user = User.builder()
                .userId(request.userId())
                .uid(request.uid())
                .pwd(request.pwd())
                .isAdult(request.isAdult())
                .build();
        return userRepository.save(user).getUserId();
    }

    @Transactional
    public void deleteUser(ReadUserRequest request){
        userRepository.deleteById(request.userId());
    }
}
