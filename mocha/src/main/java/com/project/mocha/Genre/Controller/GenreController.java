package com.project.mocha.Genre.Controller;

import com.project.mocha.Genre.DTO.CreateGenreRequest;
import com.project.mocha.Genre.DTO.CreateGenreResponse;
import com.project.mocha.Genre.DTO.ReadGenreListResponse;
import com.project.mocha.Genre.DTO.ReadGenreResponse;
import com.project.mocha.Genre.Service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mc/genre")
@RequiredArgsConstructor
@Tag(name="genre")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @Operation(summary="create genre")
    public ResponseEntity<CreateGenreResponse> createGenre(@Parameter(required=true, description="genre create request")
                                                               @RequestBody CreateGenreRequest request){
        return ResponseEntity.ok(genreService.createGenre(request));
    }

    @GetMapping("/{id}")
    @Operation(summary="get genre by id")
    public ResponseEntity<ReadGenreResponse> getGenre(@Parameter(required=true, description="genre id")
                                                              @PathVariable int id){
        return ResponseEntity.ok(genreService.getGenre(id));
    }

    @GetMapping("/{id}")
    @Operation(summary="get genreList by id")
    public ResponseEntity<ReadGenreListResponse> getGenreList(@Parameter(required=true, description="genre id")
                                                        @PathVariable int id){
        return ResponseEntity.ok(genreService.getGenreList(id));
    }
}
