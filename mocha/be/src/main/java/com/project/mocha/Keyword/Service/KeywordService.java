package com.project.mocha.Keyword.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.project.mocha.Genre.DTO.CreateKeywordRequest;
import com.project.mocha.Keyword.Entity.Keyword;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final AmazonDynamoDBClient amazonDynamoDBClient;

    @Transactional
    public String createKeyword(CreateKeywordRequest request) {
        Keyword keyword = Keyword.builder()
                .genre(request.genre())
                .keyword(request.keyword())
                .genreCategory(request.genreCategory())
                .isExpose(request.isExpose())
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        mapper.save(keyword);
        return keyword.getKeyword();   // success
    }
}
