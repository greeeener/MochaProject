package com.project.mocha.Genre.DTO;

import com.project.mocha.Genre.Entity.Genre;

import java.util.List;

public record CreateGenreResponse (
        int genreId,
        String genreName
){
}
