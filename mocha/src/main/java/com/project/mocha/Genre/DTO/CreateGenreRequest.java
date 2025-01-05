package com.project.mocha.Genre.DTO;

import com.project.mocha.Keyword.DTO.CreateKeywordRequest;
import com.project.mocha.Keyword.Entity.Keyword;

import java.util.ArrayList;
import java.util.List;

public record CreateGenreRequest (
        String genreName,
        List<KeywordRequestWithGenre> keywords
){

    public static record KeywordRequestWithGenre(
            String keyword_name,
            String is_expose
    ) {
    }

    public String getGenreName() { return genreName; }

}
