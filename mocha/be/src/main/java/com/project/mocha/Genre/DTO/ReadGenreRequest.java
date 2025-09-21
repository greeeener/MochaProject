package com.project.mocha.Genre.DTO;

import java.util.List;

public record ReadGenreRequest(
        String genre,
        List<String> genreCategory
){}
