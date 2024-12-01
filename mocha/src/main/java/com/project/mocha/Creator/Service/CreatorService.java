package com.project.mocha.Creator.Service;

import com.project.mocha.Creator.DTO.CreateCreatorRequest;
import com.project.mocha.Creator.DTO.CreateCreatorResponse;
import com.project.mocha.Creator.DTO.ReadCreatorResponse;
import com.project.mocha.Creator.DTO.UpdateCreatorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatorService {

    public CreateCreatorResponse createCreator(CreateCreatorRequest request) {
        return null;
    }

    public ReadCreatorResponse getCreator(int id){
        return null;
    }

    public CreateCreatorResponse updateCreator(int id, UpdateCreatorRequest request){
        return null;
    }

}
