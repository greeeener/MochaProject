package com.project.mocha.Creator.DTO;

public record CreateCreatorRequest (
        String creatorName
){

    public String getCreatorName() {
        return creatorName;
    }
}
