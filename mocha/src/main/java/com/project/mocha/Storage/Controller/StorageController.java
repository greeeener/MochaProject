package com.project.mocha.Storage.Controller;

import com.project.mocha.Storage.DTO.CreateStorageRequest;
import com.project.mocha.Storage.DTO.DeleteStorageRequest;
import com.project.mocha.Storage.DTO.ReadStorageRequest;
import com.project.mocha.Storage.DTO.ReadStorageResponse;
import com.project.mocha.Storage.Service.StorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mc/storage")
@Tag(name="storage")
@RequiredArgsConstructor
@RestController
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    @Operation(summary="create storage")
    public void createStorage(
            @Parameter(required=true, description="storage create request")
            @RequestBody CreateStorageRequest request){
        storageService.createStorage(request);
    }

    @GetMapping
    @Operation(summary="get storage list by user id")
    public ResponseEntity<List<ReadStorageResponse>> getStorageList(
            @Parameter(required=true, description="storage read request")
            @RequestBody ReadStorageRequest request){
        return ResponseEntity.ok(storageService.getStorageList(request));
    }

    @DeleteMapping
    @Operation(summary="delete storage by user id and creation id")
    public ResponseEntity<ReadStorageResponse> deleteUser(
            @Parameter(required=true, description="storage delete request")
            @RequestBody DeleteStorageRequest request){
        storageService.deleteStorage(request);
        return ResponseEntity.noContent().build();
    }

}
