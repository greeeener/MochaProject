package com.project.mocha.Genre.Controller;

import com.project.mocha.Genre.DTO.ReadKeywordMapResponse;
import com.project.mocha.Genre.DTO.UpdateKeywordRequest;
import com.project.mocha.Genre.Service.GenreService;
import com.project.mocha.Genre.DTO.CreateKeywordRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mc/genre")
@RequiredArgsConstructor
@Tag(name="Genre")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @Operation(summary="create keyword")
    public ResponseEntity<Void> createKeyword(
            @Parameter(required=true, description="keyword create request")
            @RequestBody CreateKeywordRequest request){
        genreService.createKeyword(request);
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/{genre}")
    @Operation(summary="get keyword list by genre")
    public ResponseEntity<ReadKeywordMapResponse> getKeywordList(
            @PathVariable String genre) {
        return ResponseEntity.ok(genreService.getKeywordList(genre));
    }

     */

    @PutMapping("{keyword}")
    @Operation(summary="update keyword")
    public ResponseEntity<String> updateKeyword(
            @PathVariable String keyword,
            @Parameter(required=true, description="update creation request")
            @RequestBody UpdateKeywordRequest request) {
        return ResponseEntity.ok(genreService.updateKeyword(keyword, request));
    }
}
