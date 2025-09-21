package com.project.mocha.Genre.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.project.mocha.Creation.DTO.CreationResponse;
import com.project.mocha.Creation.DTO.ReadCreationRequest;
import com.project.mocha.Creation.DTO.UpdateCreationRequest;
import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Genre.DTO.CreateKeywordRequest;
import com.project.mocha.Genre.DTO.ReadGenreRequest;
import com.project.mocha.Genre.DTO.ReadKeywordMapResponse;
import com.project.mocha.Genre.DTO.UpdateKeywordRequest;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreService {

    private final AmazonDynamoDBClient amazonDynamoDBClient;

    public void createKeyword(CreateKeywordRequest request) {
        Keyword keyword = Keyword.builder()
                .genre(request.genre())
                .keyword(request.keyword())
                .genreCategory(request.genreCategory())
                .isExpose(request.isExpose())
                .build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        mapper.save(keyword);
    }

    /*
    public ReadKeywordMapResponse getKeywordList(String genre, ReadGenreRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        Map<String, List<String>> keywords = Map.of();

        // 이런걸 캐시로 저장해두면 좋을 듯
        List<String> categories = request.genreCategory();
        for(String category: categories){
            String partitionKey = genre + "#" + category;

            Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
            eav.put(":v1", new AttributeValue().withS(partitionKey));
            DynamoDBQueryExpression<Genre> queryExpression = new DynamoDBQueryExpression<Genre>()
                    .withKeyConditionExpression("Id=:v1")
                    .withExpressionAttributeValues(eav);
            List<Genre> response = mapper.query(Genre.class, queryExpression);
            keywords.put(category, response);

        }

        long twoWeeksAgoMilli = (new Date()).getTime() - (14L*24L*60L*60L*1000L);
        Date twoWeeksAgo = new Date();
        twoWeeksAgo.setTime(twoWeeksAgoMilli);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String twoWeeksAgoStr = df.format(twoWeeksAgo);

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withS(partitionKey));
        eav.put(":v2",new AttributeValue().withS(twoWeeksAgoStr.toString()));

        DynamoDBQueryExpression<Reply> queryExpression = new DynamoDBQueryExpression<Reply>()
                .withKeyConditionExpression("Id = :v1 and ReplyDateTime > :v2")
                .withExpressionAttributeValues(eav);

        List<Reply> latestReplies = mapper.query(Reply.class, queryExpression);

        Creation creation = mapper.load(Creation.class, request.title(), request.category());
        if (creation != null)
            return  CreationResponse.from(request.title(), creation);
        return null;
    }

     */


    public String updateKeyword(String keyword, UpdateKeywordRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);

        Genre genre = mapper.load(Genre.class, request.genre(), keyword);
        genre.setGenre(request.genre());
        genre.setKeyword(request.keyword());
        genre.setGenreCategory(request.genreCategory());
        genre.setExpose(request.isExpose());

        mapper.save(genre);
        return genre.getKeyword();   // success
    }
}
