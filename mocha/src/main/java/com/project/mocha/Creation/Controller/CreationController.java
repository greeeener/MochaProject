package com.project.mocha.Creation.Controller;

import com.project.mocha.Creation.Entity.Creation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/mc/main")
@RequiredArgsConstructor
@Tag(name="main")
public class CreationController {
    @Operation(summary = "작품 조회 by CreationId", description = "특정 작품을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Creation.class))),
            @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    })
    @GetMapping("/{crationId}")
    public ResponseEntity<Creation> getCreationByCreationId(
            @Parameter(description = "작품 ID", required = true) @PathVariable Long crationId) {
        Optional<Creation> creation = CreationService.getCreation(crationId);
        return creation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
