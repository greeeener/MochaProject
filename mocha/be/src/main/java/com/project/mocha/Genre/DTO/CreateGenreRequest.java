package com.project.mocha.Genre.DTO;

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
