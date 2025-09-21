package com.project.mocha.Creation.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamoDBTable(tableName="Creation")
public class Creation {
    // high frequency data
    @DynamoDBHashKey(attributeName="creation_title")
    private String title;
    @DynamoDBRangeKey(attributeName="category")
    private String category;
    @DynamoDBAttribute(attributeName="thumbnail")
    private String thumbnail;
    @DynamoDBAttribute(attributeName="genre")
    private String genre;
    @DynamoDBAttribute(attributeName="creators")
    private List<String> creators;
    @DynamoDBAttribute(attributeName="keywords")
    private List<String> keywords;

    // root data
    @DynamoDBAttribute(attributeName="publisher")
    private String publisher;
    @DynamoDBAttribute(attributeName="age_limit")
    private String ageLimit;
    @DynamoDBAttribute(attributeName="description")
    private String description;
    @DynamoDBAttribute(attributeName="release_date")
    private String releaseDate;
    @DynamoDBAttribute(attributeName="latest_date")
    private String latestDate;

    @DynamoDBAttribute(attributeName="platforms")
    private List<Map<String, String>> platforms;
    @DynamoDBAttribute(attributeName="moca_update_date")
    private String mocaUpdateDate;
}
