package com.project.mocha.Platform.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@DynamoDBTable(tableName="Platform")
public class Platform {
    @DynamoDBHashKey(attributeName="name")
    private String name;
}