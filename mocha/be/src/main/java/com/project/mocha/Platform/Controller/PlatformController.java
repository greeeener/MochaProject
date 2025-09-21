package com.project.mocha.Platform.Controller;

import com.project.mocha.Platform.DTO.CreatePlatformRequest;
import com.project.mocha.Platform.Service.PlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mc/platform")
@Tag(name="Platform")
@RequiredArgsConstructor
@RestController
public class PlatformController {

    private final PlatformService platformService;

    @PostMapping
    @Operation(summary="create platform")
    public ResponseEntity<String> createPlatform(
            @Parameter(required=true, description="platform create request")
            @RequestBody CreatePlatformRequest request){
        return ResponseEntity.ok(platformService.createPlatform(request));
    }
}
