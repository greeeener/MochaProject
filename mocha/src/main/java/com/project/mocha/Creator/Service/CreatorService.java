package com.project.mocha.Creator.Service;

import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Creator.DTO.CreateCreatorRequest;
import com.project.mocha.Creator.DTO.CreateCreatorResponse;
import com.project.mocha.Creator.DTO.ReadCreatorResponse;
import com.project.mocha.Creator.DTO.UpdateCreatorRequest;
import com.project.mocha.Creator.Repository.CreatorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatorService {

    private final CreatorRepository creatorRepository;

    public CreateCreatorResponse createCreator(CreateCreatorRequest request) {

        // Creator 엔티티 생성
        Creator creator = Creator.builder()
                .creator_name(request.getCreatorName())
                .build();

        // 데이터베이스에 저장
        creatorRepository.save(creator);

        // 응답 객체 생성 및 반환
        return new CreateCreatorResponse(
                creator.getCreator_id(),
                creator.getCreator_name()
        );
    }

    public ReadCreatorResponse getCreator(int id){

        //id로 Creator Entity 검색
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creator not found with id: " + id));

        // ReadCreatorResponse로 변환하여 반환
        return new ReadCreatorResponse(
                creator.getCreator_id(),
                creator.getCreator_name()
        );
    }

    public CreateCreatorResponse updateCreator(int id, UpdateCreatorRequest request){
        // ID로 Creator 엔티티를 검색
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creator not found with id: " + id));

        // 엔티티 업데이트
        creator.setCreator_name(request.getCreatorName());

        // 업데이트된 데이터를 저장
        Creator updatedCreator = creatorRepository.save(creator);

        // CreateCreatorResponse로 변환하여 반환
        return new CreateCreatorResponse(
                updatedCreator.getCreator_id(),
                updatedCreator.getCreator_name()
        );
    }

}
