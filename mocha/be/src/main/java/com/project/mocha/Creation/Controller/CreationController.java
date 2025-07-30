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

    // 작품 검색
    @Operation(summary="작품 검색", description = "제목, 작가명으로 작품을 검색합니다.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description="검색 성공",
                    content = @Content(schema = @Schema(implementation= CreationListResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/search")
    public ResponseEntity<CreationListResponse> searchCreations(
            @Parameter(description="검색어(제목, 작가명)", example="괴담")
            @RequestParam(required=false) String q,

            @Parameter(description = "페이지 번호", example="0") // page는 0부터 시작
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "페이지 크기", example="10")
            @RequestParam(defaultValue = "10") int size) {

        try {
            CreationListResponse response = creationService.searchCreations(q, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("검색 중 오류 발생: " + e.getMessage());
            e.printStackTrace();

            CreationListResponse emptyResponse = new CreationListResponse(
                    List.of(), 0, 0L, page
            );
            return ResponseEntity.ok(emptyResponse);
        }
    }

    /*
    // 디버깅용 API
    @GetMapping("/debug/{id}")
    public ResponseEntity<Map<String, Object>> debugCreation(@PathVariable int id) {
        try {
            System.out.println("ID 조회 시작: " + id);

            Optional<Creation> creation = creationRepository.findById(id);
            if (creation.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            System.out.println("Creation 조회 성공: " + creation.get().getTitle());
            System.out.println("CreatorList 크기: " +
                    (creation.get().getCreatorList() != null ? creation.get().getCreatorList().size() : "null"));

            // DTO 변환 없이 원시 데이터 반환
            Map<String, Object> debug = new HashMap<>();
            debug.put("id", creation.get().getCreationId());
            debug.put("title", creation.get().getTitle());
            debug.put("publisher", creation.get().getPublisher());

            return ResponseEntity.ok(debug);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("cause", e.getClass().getSimpleName());
            return ResponseEntity.status(500).body(error);
        }
    }
     */

}
