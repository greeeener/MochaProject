package com.project.mocha.Storage.Service;

import com.project.mocha.Storage.DTO.CreateStorageRequest;
import com.project.mocha.Storage.DTO.CreateStorageResponse;
import com.project.mocha.Storage.DTO.ReadStorageListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StorageService {
    public CreateStorageResponse createStorage(CreateStorageRequest request) {
        return null;
    }

    public ReadStorageListResponse getStorageList(int userId){
        return null;
    }

    public void deleteStorage(int userId, int creationId){}
}
