package com.project.mocha.Creation.DTO;

import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import com.project.mocha.Platform.Entity.Platform;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public record UpdateCreationRequest(
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