package com.project.mocha.Storage.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class Storage {
    @EmbeddedId
    private StorageId storageId;

    @Embeddable
    @Data
    @NoArgsConstructor(access=AccessLevel.PROTECTED)
    static class StorageId implements Serializable{
        private int userId;
        private int creationId;
    }
}
