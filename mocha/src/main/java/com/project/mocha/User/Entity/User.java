package com.project.mocha.User.Entity;

import com.project.mocha.Storage.Entity.Storage;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name="user_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;

    @Column(name="uid", nullable=false, length=20)
    private String uid;     // user가 직접 지은 ID

    @Column(name="pwd", nullable=false, length=20)
    private String pwd;

    @Column(name="is_adult", nullable=false)
    private boolean isAdult;       // default를 false로?

    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private List<Storage> StorageList;
}