package com.project.mocha.User.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.HashSet;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@DynamoDBTable(tableName="User")
public class User {
    @DynamoDBHashKey(attributeName="id")
    private String id;
    @DynamoDBAttribute
    private String pwd;
    @DynamoDBAttribute(attributeName="created_at")
    private String createdAt;
    @DynamoDBAttribute(attributeName="adult")
    private boolean isAdult;
    @DynamoDBAttribute(attributeName="storage")
    private HashSet<String> storage;
}