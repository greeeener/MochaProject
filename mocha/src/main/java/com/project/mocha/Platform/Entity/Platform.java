package com.project.mocha.Platform.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class Platform {
    @Id
    @Column(name="platform_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(name="PLATFORM_SEQ", allocationSize=1)
    private int platformId;

    @Column(name="platform_name", nullable=false, length=20)
    private String platformName;

    @Column(name="coin_cost", nullable=false)
    private int coinCost;

    // Platform이 가지고 있는 작품들 리스트를 보관할 필요가 있을까?
}