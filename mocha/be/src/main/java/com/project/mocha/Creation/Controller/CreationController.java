package com.project.mocha.Creation.Controller;

import com.project.mocha.Creation.DTO.*;
import com.project.mocha.Creation.Service.CreationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mc/creation")
@Tag(name="main")
@RequiredArgsConstructor
@RestController
public class CreationController {

    public final CreationService creationService;

    @PostMapping
    @Operation(summary="create creation", description="작품을 생성합니다.")
    public ResponseEntity<Void> createCreation(
            @Parameter(required=true, description="create creation request")
            @RequestBody CreateCreationRequest request) {
        creationService.createCreation(request);
        return ResponseEntity.noContent().build();
        /*
        return value: ResponseEntity<Void>
        return ResponseEntity.created(URI.create("mc/creation/getCreation/"+createCreationId)).build();
         */
    }

    @GetMapping("/{title}")
    @Operation(summary="get creation by title", description="특정 작품을 조회합니다.")
    public ResponseEntity<CreationResponse> getCreation(
            @Parameter(required=true, description="get creation request")
            @RequestBody ReadCreationRequest request) {
        CreationResponse response = creationService.findCreation(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "작품 리스트 검색", description = "작품 리스트를 조회합니다.")
    @PostMapping("/getCreationList")
    public ResponseEntity<CreationListResponse> getCreationList(CreationListRequest request, Pageable pageable) {
        CreationListResponse response = creationService.findAllWithFiltering(request, pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{title}")
    @Operation(summary="update creation by title", description="작품을 업데이트합니다.")
    public ResponseEntity<String> updateCreation(
            @PathVariable String title,
            @Parameter(required=true, description="update creation request")
            @RequestBody UpdateCreationRequest request) {
        return ResponseEntity.ok(creationService.updateCreation(title, request));
        /*
        return ResponseEntity.noContent()
                .header(HttpHeaders.LOCATION,"mc/creation/getCreation/"+UpdateCreationId).build();
         */
    }

    @DeleteMapping
    @Operation(summary="delete creation by title")
    public ResponseEntity<CreationResponse> deleteCreation(
            @Parameter(required=true, description="update user request")
            @RequestBody ReadCreationRequest request){
        creationService.deleteCreation(request);
        return ResponseEntity.noContent().build();
    }
}