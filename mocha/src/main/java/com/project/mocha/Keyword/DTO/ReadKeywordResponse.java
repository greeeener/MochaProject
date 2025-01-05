package com.project.mocha.Keyword.DTO;

import java.util.List;

public record ReadKeywordResponse(
        List<KeywordResponse> keywordResponses
) {
    public record KeywordResponse(
            int keywordId,
            String keywordName,
            String isExpose,
            int genreId
    ) {}
}