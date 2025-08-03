package com.project.mocha.Creation.Controller;

import com.project.mocha.Creation.DTO.*;
import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Creation.Repository.CreationRepository; // 추가 필요
import com.project.mocha.Creation.Service.CreationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap; // 추가 필요
import java.util.List;
import java.util.Map; // 추가 필요
import java.util.Optional;

@RestController
@RequestMapping("/mc/creation")
@RequiredArgsConstructor
@Tag(name="main")
public class CreationController {

    private final CreationService creationService; // public → private final
    private final CreationRepository creationRepository; // 추가 필요

    @Operation(summary = "작품 조회 by CreationId", description = "특정 작품을 조회합니다.")
    @GetMapping("/getCreation/{creationId}")
    public ResponseEntity<CreationResponse> getCreation(
            @PathVariable int creationId) {
        CreationResponse response = creationService.findCreation(creationId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "작품 리스트 검색", description = "작품 리스트를 조회합니다.")
    @PostMapping("/getCreationList")
    public ResponseEntity<CreationListResponse> getCreationList(@RequestBody CreationListRequest request, Pageable pageable) { // @RequestBody 추가
        CreationListResponse response = creationService.findAllWithFiltering(request, pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "작품 생성", description = "작품을 생성합니다.")
    @PostMapping("/createCreation")
    public ResponseEntity<Void> createCreation(@RequestBody CreateCreationRequest request) { // @RequestBody 추가
        int createCreationId = creationService.createCreation(request);
        return ResponseEntity.created(URI.create("mc/creation/getCreation/"+createCreationId)).build();
    }

    @Operation(summary = "작품 수정", description = "작품을 업데이트합니다.")
    @PutMapping("/updateCreation/{creationId}")
    public ResponseEntity<Void> updateCreation(@PathVariable int creationId, @RequestBody UpdateCreationRequest request) {
        int UpdateCreationId = creationService.updateCreation(creationId, request);
        return ResponseEntity.noContent()
                .header(HttpHeaders.LOCATION,"mc/creation/getCreation/"+UpdateCreationId).build();
    }

}
