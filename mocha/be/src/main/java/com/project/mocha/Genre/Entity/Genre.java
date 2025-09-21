package com.project.mocha.Genre.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.project.mocha.Keyword.Entity.Keyword;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@DynamoDBTable(tableName="Genre")
public class Genre {
    @DynamoDBHashKey(attributeName="genre")
    private String genre;
    @DynamoDBRangeKey(attributeName="keyword")
    private String keyword;
    @DynamoDBAttribute(attributeName="genre_category")
    private String genreCategory;
    @DynamoDBAttribute(attributeName="expose")
    private boolean isExpose;
}
