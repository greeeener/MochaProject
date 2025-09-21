package com.project.mocha.Keyword.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@DynamoDBTable(tableName="Keyword")
public class Keyword {
    @DynamoDBHashKey(attributeName="genre")
    private String genre;
    @DynamoDBRangeKey(attributeName="keyword")
    private String keyword;
    @DynamoDBAttribute(attributeName="genre_category")
    private String genreCategory;
    @DynamoDBAttribute(attributeName="expose")
    private boolean isExpose;
}