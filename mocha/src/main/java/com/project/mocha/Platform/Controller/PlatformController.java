package com.project.mocha.Platform.Controller;

import com.project.mocha.Platform.DTO.CreatePlatformRequest;
import com.project.mocha.Platform.DTO.CreatePlatformResponse;
import com.project.mocha.Platform.DTO.UpdatePlatformRequest;
import com.project.mocha.Platform.Service.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mc/platform")
@RequiredArgsConstructor
@Tag(name="platform")
public class PlatformController {

    private final PlatformService platformService;

    @PostMapping
    @Operation(summary="create platform")
    public ResponseEntity<CreatePlatformResponse> createPlatform(@Parameter(required=true, description="platform create request")
                                                               @RequestBody CreatePlatformRequest request){
        return ResponseEntity.ok(platformService.createPlatform(request));
    }

    @PutMapping("/{id}")
    @Operation(summary="update platform by id")
    public ResponseEntity<CreatePlatformResponse> updatePlatform(@Parameter(required=true, description="platform id")
                                                               @PathVariable int id,
                                                               @Parameter(required=true, description="platform update request")
                                                               @RequestBody UpdatePlatformRequest request){
        return ResponseEntity.ok(platformService.updatePlatform(id, request));
    }
}
