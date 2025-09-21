package com.project.mocha.Keyword.Controller;

import com.project.mocha.Genre.DTO.CreateKeywordRequest;
import com.project.mocha.Keyword.Service.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mc/keyword")
@Tag(name="keyword")
@RequiredArgsConstructor
@RestController
public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping
    @Operation(summary="create keyword")
    public ResponseEntity<String> createKeyword(
            @Parameter(required=true, description="keyword create request")
            @RequestBody CreateKeywordRequest request){
        return ResponseEntity.ok(keywordService.createKeyword(request));
    }
}
