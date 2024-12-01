package com.project.mocha.Storage.Controller;

import com.project.mocha.Storage.DTO.CreateStorageResponse;
import com.project.mocha.Storage.DTO.CreateStorageRequest;
import com.project.mocha.Storage.DTO.ReadStorageListResponse;
import com.project.mocha.Storage.Service.StorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mc/storage")
@Tag(name="storage")
@RequiredArgsConstructor
@RestController
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    @Operation(summary="create storage")
    public ResponseEntity<CreateStorageResponse> createStorage(
            @Parameter(required=true, description="storage create request")
            @RequestBody CreateStorageRequest request){
        return ResponseEntity.ok(storageService.createStorage(request));
    }

    @GetMapping
    @Operation(summary="get storage list by user id")
    public ResponseEntity<ReadStorageListResponse> getStorageList(
            @Parameter(required=true, description="user id") int userId){
        return ResponseEntity.ok(storageService.getStorageList(userId));
    }

    @DeleteMapping
    @Operation(summary="delete storage by user id and creation id")
    public ResponseEntity<ReadStorageListResponse> deleteUser(
            @Parameter(required=true, description="user id") int userId,
            @Parameter(required=true, description="creation id") int creationId){
        // ReadStorageResponse가 없어서 List로 대체했는데 괜찮을까?
        storageService.deleteStorage(userId, creationId);
        return ResponseEntity.noContent().build();
    }

}
