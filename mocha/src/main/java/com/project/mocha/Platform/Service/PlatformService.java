package com.project.mocha.Platform.Service;

import com.project.mocha.Creator.DTO.CreateCreatorResponse;
import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Platform.DTO.CreatePlatformRequest;
import com.project.mocha.Platform.DTO.CreatePlatformResponse;
import com.project.mocha.Platform.DTO.UpdatePlatformRequest;
import com.project.mocha.Platform.Entity.Platform;
import com.project.mocha.Platform.Repository.PlatformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlatformService {

    private final PlatformRepository platformRepository;

    public CreatePlatformResponse createPlatform(CreatePlatformRequest request) {

        // Creator 엔티티 생성
        Platform platform = Platform.builder()
                .platform_name(request.getPlatformName())
                .coin_cost(request.getCoinCost())
                .build();

        // 데이터베이스에 저장
        platformRepository.save(platform);

        // 응답 객체 생성 및 반환
        return new CreatePlatformResponse(
                platform.getPlatform_id(),
                platform.getPlatform_name(),
                platform.getCoin_cost()
        );
    }

    public CreatePlatformResponse updatePlatform(int id, UpdatePlatformRequest request){
        // ID로 Creator 엔티티를 검색
        Platform platform = platformRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Platform not found with id: " + id));

        // 엔티티 업데이트
        platform.setPlatform_name(request.getPlatformName());
        platform.setCoin_cost(request.getCoinCost());

        // 업데이트된 데이터를 저장
        Platform updatedPlatform = platformRepository.save(platform);

        // CreateCreatorResponse로 변환하여 반환
        return new CreatePlatformResponse(
                updatedPlatform.getPlatform_id(),
                updatedPlatform.getPlatform_name(),
                updatedPlatform.getCoin_cost()
        );
    }
}
