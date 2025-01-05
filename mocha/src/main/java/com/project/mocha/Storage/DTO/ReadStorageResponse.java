package com.project.mocha.Storage.DTO;

import com.project.mocha.Storage.Entity.Storage;
import lombok.Builder;

@Builder
public record ReadStorageResponse(
        Storage.StorageId storageId
) {
    public static ReadStorageResponse of(Storage storage) {
        return ReadStorageResponse.builder()
                .storageId(new Storage.StorageId(
                        storage.getStorageId().getUserId(), storage.getStorageId().getCreationId()))
                .build();
    }
}
