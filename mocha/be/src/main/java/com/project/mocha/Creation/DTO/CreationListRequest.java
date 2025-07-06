package com.project.mocha.Creation.DTO;

import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;

import java.util.List;

public record CreationListRequest(String searchKeyword, String publisher, boolean is_end, List<Integer> genreList, List<Integer> keywordList){
}