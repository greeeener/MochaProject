package com.project.mocha.Creation.DTO;

import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import com.project.mocha.Platform.Entity.Platform;

import java.sql.Date;
import java.util.List;

public record UpdateCreationRequest(int id, String title, String publisher, int age_limit, int episodes, boolean is_end, int free_episodes, int gidamu, String period, int rent_cost, int buy_cost, Date start_date, Date latest_date, String description, List<Integer> creatorList, List<Integer> platformList, List<Integer> genreList, List<Integer> keywordList){
}