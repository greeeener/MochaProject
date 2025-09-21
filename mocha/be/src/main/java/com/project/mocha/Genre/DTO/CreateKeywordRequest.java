package com.project.mocha.Genre.DTO;

public record CreateKeywordRequest (
        String genre,
        String keyword,
        String genreCategory,
        boolean isExpose
){}