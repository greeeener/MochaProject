package com.project.mocha.Keyword.Controller;

import com.project.mocha.Keyword.DTO.CreateKeywordRequest;
import com.project.mocha.Keyword.DTO.CreateKeywordResponse;
import com.project.mocha.Keyword.DTO.ReadKeywordResponse;
import com.project.mocha.Keyword.Service.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mc/keyword")
@RequiredArgsConstructor
@Tag(name="keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping
    @Operation(summary="create keyword")
    public ResponseEntity<CreateKeywordResponse> createKeyword(@Parameter(required=true, description="keyword create request")
                                                               @RequestBody CreateKeywordRequest request){
        return ResponseEntity.ok(keywordService.createKeyword(request));
    }

    @GetMapping("/{genre_id}")
    @Operation(summary="get keyword list by genre id")
    public ResponseEntity<ReadKeywordResponse> getKeywordList(@Parameter(required=true, description="get keyword list by genre id")
                                                          @PathVariable int genre_id){
        return ResponseEntity.ok(keywordService.getKeywordList(genre_id));
    }
}
