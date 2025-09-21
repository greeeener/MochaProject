package com.project.mocha.Genre.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "genre_id")
@DynamoDBTable(tableName="Genre")
public class Genre {
    @DynamoDBHashKey(attributeName="genre")
    private String genre;
    @DynamoDBRangeKey(attributeName="keyword")
    private String keyword;
    @DynamoDBAttribute(attributeName="expose")
    private boolean isExpose;
}
