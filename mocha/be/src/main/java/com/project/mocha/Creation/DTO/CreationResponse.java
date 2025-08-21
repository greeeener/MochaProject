package com.project.mocha.Creation.DTO;

import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import com.project.mocha.Platform.Entity.Platform;

import java.sql.Date;
import java.util.List;

public record CreationResponse (int id, String title, String publisher, int age_limit, int episodes, boolean is_end, int free_episodes, int gidamu, String period, int rent_cost, int buy_cost, Date start_date, Date latest_date, String description, List<Creator> creatorList, List<Platform> platformList, List<Genre> genreList, List<Keyword> keywordList){
    public static CreationResponse from(Creation creation){
        return new CreationResponse(
                creation.getCreationId(),
                creation.getTitle(),
                creation.getPublisher(),
                creation.getAge_limit(),
                creation.getEpisodes(),
                creation.is_end(),
                creation.getFree_episodes(),
                creation.getGidamu(),
                creation.getPeriod(),
                creation.getRent_cost(),
                creation.getBuy_cost(),
                creation.getStart_date(),
                creation.getLatest_date(),
                creation.getDescription(),
                creation.getCreatorList(),
                creation.getPlatformList(),
                creation.getGenreList(),
                creation.getKeywordList()
        );
    }
}