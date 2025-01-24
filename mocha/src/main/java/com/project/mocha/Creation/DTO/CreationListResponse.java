package com.project.mocha.Creation.DTO;

import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import org.springframework.data.domain.Page;

import java.util.List;

public record CreationListResponse(List<CreationResponse> creationResponsesList, int totalPages, long totalElements, int pageNumber){
    
    public  static CreationListResponse from(Page<Creation> page){
        Page<CreationResponse> responses = page.map(CreationResponse::from);
        return new CreationListResponse(responses.getContent(),
                responses.getTotalPages(), responses.getTotalElements(), responses.getNumber());

    }
}
