package com.project.mocha.Keyword.DTO;

public record CreateKeywordRequest (
        String keywordName,
        String isExpose,
        int genreId
){

    public String getKeywordName() {
        return keywordName;
    }

    public String getIsExpose() {
        return isExpose;
    }

    public Integer getGenreId() {
        return genreId;
    }
}