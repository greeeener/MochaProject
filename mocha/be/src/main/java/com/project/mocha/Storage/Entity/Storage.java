package com.project.mocha.Storage.Entity;

import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Data //getter,setter
@Entity
@Table(name="Storage")
public class Storage {
    @EmbeddedId
    private StorageId storageId;

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class StorageId implements Serializable{
        @Column(name="User_user_id")
        private int userId;
        @Column(name="Creation_creation_id")
        private int creationId;
    }
}