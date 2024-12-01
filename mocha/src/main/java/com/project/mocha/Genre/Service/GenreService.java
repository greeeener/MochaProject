package com.project.mocha.Genre.Service;

import com.project.mocha.Genre.DTO.CreateGenreRequest;
import com.project.mocha.Genre.DTO.CreateGenreResponse;
import com.project.mocha.Genre.DTO.ReadGenreListResponse;
import com.project.mocha.Genre.DTO.ReadGenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenreService {

    public CreateGenreResponse createGenre(CreateGenreRequest request){
        return null;
    }

    public ReadGenreResponse getGenre(int id){
        return null;
    }

    public ReadGenreListResponse getGenreList(int id){
        return null;
    }
}
