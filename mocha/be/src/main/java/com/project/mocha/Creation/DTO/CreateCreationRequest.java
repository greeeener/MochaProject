package com.project.mocha.Creation.DTO;

import java.util.List;
import java.util.Map;

public record CreateCreationRequest(
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
){}