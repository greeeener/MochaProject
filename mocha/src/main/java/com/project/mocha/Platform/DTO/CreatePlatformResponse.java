package com.project.mocha.Platform.DTO;

public record CreatePlatformResponse (
        int platformId,
        String platformName,
        double coinCost
){
}
