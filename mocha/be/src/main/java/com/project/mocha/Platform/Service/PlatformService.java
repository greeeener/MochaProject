package com.project.mocha.Platform.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.project.mocha.Platform.DTO.CreatePlatformRequest;
import com.project.mocha.Platform.Entity.Platform;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlatformService {

    private final AmazonDynamoDBClient amazonDynamoDBClient;

    @Transactional
    public String createPlatform(CreatePlatformRequest request) {
        Platform platform = Platform.builder()
                .name(request.name())
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        mapper.save(platform);
        return platform.getName();   // success
    }
}
