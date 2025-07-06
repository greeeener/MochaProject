package com.project.mocha.Creator.Controller;

import com.project.mocha.Creator.DTO.CreateCreatorRequest;
import com.project.mocha.Creator.DTO.CreateCreatorResponse;
import com.project.mocha.Creator.DTO.ReadCreatorResponse;
import com.project.mocha.Creator.DTO.UpdateCreatorRequest;
import com.project.mocha.Creator.Service.CreatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mc/creator")
@RequiredArgsConstructor
@Tag(name="Creator")
public class CreatorController {

    private final CreatorService creatorService;

    @PostMapping
    @Operation(summary="create creator")
    public ResponseEntity<CreateCreatorResponse> createCreator(@Parameter(required=true, description="creator create request")
                                                               @RequestBody CreateCreatorRequest request){
        return ResponseEntity.ok(creatorService.createCreator(request));
    }

    @GetMapping("/{id}")
    @Operation(summary="get creator by id")
    public ResponseEntity<ReadCreatorResponse> getCreator(@Parameter(required=true, description="creator id")
                                                          @PathVariable int id){
        return ResponseEntity.ok(creatorService.getCreator(id));
    }

    @PutMapping("/{id}")
    @Operation(summary="update creator by id")
    public ResponseEntity<CreateCreatorResponse> updateCreator(@Parameter(required=true, description="creator id")
                                                               @PathVariable int id,
                                                               @Parameter(required=true, description="creator update request")
                                                               @RequestBody UpdateCreatorRequest request){
        return ResponseEntity.ok(creatorService.updateCreator(id, request));
    }
}
