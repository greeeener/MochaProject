package com.project.mocha.User.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.project.mocha.User.DTO.*;
import com.project.mocha.User.Entity.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    /*
    나중에 Session 사용하게 되면 DTO 구조랑 로직 바꿔야할 듯
     */

    private final AmazonDynamoDBClient amazonDynamoDBClient;

    @Transactional
    public String createUser(CreateUserRequest request){
        User user = User.builder()
                .id(request.id())
                .pwd(request.pwd())
                .isAdult(request.isAdult())
                .createdAt(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .storage(new HashSet<String>(List.of("")))
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        mapper.save(user);
        return user.getId();   // success
    }

    public ReadUserResponse getUser(ReadUserRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        User user = mapper.load(User.class, request.id());
        if (user != null)
            return new ReadUserResponse(user.getId(), user.isAdult());
        return null;
    }

    @Transactional
    public String updateUser(UpdateUserRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        User user = mapper.load(User.class, request.id());
        user.setId(request.id());
        user.setPwd(request.pwd());
        user.setAdult(request.isAdult());
        mapper.save(user);
        return user.getId();   // success
    }

    @Transactional
    public void deleteUser(ReadUserRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        mapper.delete(mapper.load(User.class, request.id()));
    }
}
