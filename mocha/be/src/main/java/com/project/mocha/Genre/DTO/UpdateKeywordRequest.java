package com.project.mocha.Genre.DTO;

public record UpdateKeywordRequest (
        String genre,
        String keyword,
        String genreCategory,
        boolean isExpose
){}