package com.project.mocha.Genre.Controller;

import com.project.mocha.Genre.DTO.CreateGenreRequest;
import com.project.mocha.Genre.DTO.CreateGenreResponse;
import com.project.mocha.Genre.DTO.ReadGenreResponse;
import com.project.mocha.Genre.Service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mc/genre")
@RequiredArgsConstructor
@Tag(name="Genre")
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

    @GetMapping("/list")
    @Operation(summary="get genreList")
    public ResponseEntity<List<ReadGenreResponse>> getGenreList(){
        return ResponseEntity.ok(genreService.getGenreList());
    }

    @PostMapping("/withKeywords")
    @Operation(summary="create genre and keywords")
    public ResponseEntity<CreateGenreResponse> saveGenreWithKeywords(
            @Parameter(required = true, description = "Genre create request")
            @RequestBody CreateGenreRequest request) {

        // Service 메서드 호출
        CreateGenreResponse response = genreService.saveGenreWithKeywords(
                request.genreName(), request.keywords()); // record의 메서드 활용

        // ResponseEntity로 응답 반환
        return ResponseEntity.ok(response);
    }
}
