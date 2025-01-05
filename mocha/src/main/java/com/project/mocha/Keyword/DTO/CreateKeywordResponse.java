package com.project.mocha.Keyword.DTO;

public record CreateKeywordResponse(
        int keywordId,
        String keywordName,
        String isExpose,
        int genreId
) {
}
