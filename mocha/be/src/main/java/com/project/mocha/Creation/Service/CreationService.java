package com.project.mocha.Creation.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.project.mocha.Creation.DTO.*;
import com.project.mocha.Creation.Entity.Creation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class CreationService {
    private final AmazonDynamoDBClient amazonDynamoDBClient;

    public void createCreation(CreateCreationRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

        Creation creation = Creation.builder()
                .title(request.title())
                .category(request.category())
                .genre(request.genre())
                .thumbnail(request.thumbnail())
                .creators(request.creators())
                .keywords(request.keywords())
                .publisher(request.publisher())
                .ageLimit(request.ageLimit())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .latestDate(request.latestDate())
                .platforms(request.platforms())
                .mocaUpdateDate(LocalDateTime.now().format(formatter))
                .build();
        mapper.save(creation);
    }

    public CreationResponse findCreation(ReadCreationRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        Creation creation = mapper.load(Creation.class, request.title(), request.category());
        if (creation != null)
            return  CreationResponse.from(request.title(), creation);
        return null;
    }

    public CreationListResponse findAllWithFiltering(CreationListRequest request, Pageable pageable){
        /*
        List<Genre> genreList = genreRepository.findAllById(request.genreList());
        List<Keyword> keywordList = keywordRepository.findAllById(request.keywordList());
        Page<Creation> creations = creationRepository.findAllWithFiltering(request.searchKeyword(), request.publisher(), request.is_end(), genreList, keywordList, pageable);
        return CreationListResponse.from(creations);
         */
        return null;
    }

    public String updateCreation(String title, UpdateCreationRequest request){
        // 좀 더 효율적인 방법 생각 좀 ..
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

        Creation creation = mapper.load(Creation.class, title, request.category());
        creation.setTitle(request.title());
        creation.setCategory(request.category());
        creation.setGenre(request.genre());
        creation.setThumbnail(request.thumbnail());
        creation.setCreators(request.creators());
        creation.setKeywords(request.keywords());
        creation.setPublisher(request.publisher());
        creation.setAgeLimit(request.ageLimit());
        creation.setDescription(request.description());
        creation.setReleaseDate(request.releaseDate());
        creation.setLatestDate(request.latestDate());
        creation.setPlatforms(request.platforms());
        creation.setMocaUpdateDate(LocalDateTime.now().format(formatter));

        mapper.save(creation);
        return creation.getTitle();   // success
    }

    public void deleteCreation(ReadCreationRequest request){
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);
        mapper.delete(mapper.load(Creation.class, request.title(), request.category()));
    }
}