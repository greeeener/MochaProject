package com.project.mocha.Storage.Service;

import com.project.mocha.Storage.DTO.CreateStorageRequest;
import com.project.mocha.Storage.DTO.DeleteStorageRequest;
import com.project.mocha.Storage.DTO.ReadStorageRequest;
import com.project.mocha.Storage.DTO.ReadStorageResponse;
import com.project.mocha.Storage.Entity.Storage;
import com.project.mocha.Storage.Repository.StorageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@RequiredArgsConstructor
@Service
public class StorageService {

    private final StorageRepository storageRepository;

    @Transactional
    public void createStorage(CreateStorageRequest request) {
        Storage storage = Storage.builder()
                .storageId(new Storage.StorageId(request.userId(), request.creationId()))
                .build();
        storageRepository.save(storage);
    }

    public List<ReadStorageResponse> getStorageList(ReadStorageRequest request){
        List<Storage> result =
                storageRepository.findByStorageIdUserId(request.userId());
        if(result != null)
            return result.stream().map(ReadStorageResponse::of)
                    .collect(Collectors.toList());
        return null;
    }

    @Transactional
    public void deleteStorage(DeleteStorageRequest request){
        storageRepository.deleteByStorageIdUserIdAndStorageIdCreationId(
                request.userId(), request.creationId());
    }
}
