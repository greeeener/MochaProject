package com.project.mocha.Creation.DTO;

import com.project.mocha.Creation.Entity.Creation;
import java.util.List;
import java.util.Map;

public record CreationResponse (
        String title,
        String category,
        String genre,
        String thumbnail,
        List<String> creators,
        List<String> keywords,
        String publisher,
        String ageLimit,
        String description,
        String releaseDate,
        String latestDate,
        List<Map<String,String>> platforms
){
    public static CreationResponse from(String title, Creation creation){
        return new CreationResponse(
                title,
                creation.getCategory(),
                creation.getGenre(),
                creation.getThumbnail(),
                creation.getCreators(),
                creation.getKeywords(),
                creation.getPublisher(),
                creation.getAgeLimit(),
                creation.getDescription(),
                creation.getReleaseDate(),
                creation.getLatestDate(),
                creation.getPlatforms());
    }
}