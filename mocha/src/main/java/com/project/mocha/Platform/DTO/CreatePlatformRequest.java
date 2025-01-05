package com.project.mocha.Platform.DTO;

public record CreatePlatformRequest (
        String platformName,
        double coinCost
){

    public String getPlatformName(){ return platformName; }

    public double getCoinCost(){ return coinCost; }
}
