package com.project.mocha.Creation.Controller;


import com.project.mocha.Creation.DTO.*;
import com.project.mocha.Creation.Entity.Creation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mc/creation")
@RequiredArgsConstructor
@Tag(name="main")
public class CreationController {
    @Operation(summary = "작품 조회 by CreationId", description = "특정 작품을 조회합니다.")
    /*@ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Creation.class))),
            @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    })*/
    @GetMapping("/getCreation/{creationId}")
    public ResponseEntity<CreationResponse> getCreation(
            @Parameter(description = "작품 ID", required = true)int creationId) {

        return null;
    }
    @Operation(summary = "작품 리스트 검색", description = "작품 리스트를 조회합니다.")
    @PostMapping("/getCreationList")
    public ResponseEntity<CreationListResponse> getCreationList(CreationListRequest request, Pageable pageable) {
        return null;
    }

    @Operation(summary = "작품 생성", description = "작품을 생성합니다.")
    @PostMapping("/createCreation")
    public ResponseEntity<CreationResponse> createCreation(CreateCreationRequest request) {
        return null;
    }

    @Operation(summary = "작품 생성", description = "작품을 생성합니다.")
    @PutMapping("/updateCreation/{creationId}")
    public ResponseEntity<Void> updateCreation(UpdateCreationRequest request) {
        return null;
    }
}
