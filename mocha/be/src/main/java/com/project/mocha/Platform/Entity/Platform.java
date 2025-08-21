package com.project.mocha.Platform.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "platform_id")
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PROTECTED)
@Table(name="Platform")
public class Platform {
    @Id
    //@Column(name="platform_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@SequenceGenerator(name="PLATFORM_SEQ", allocationSize=1)
    private int platform_id;

    @Column(name="platform_name", nullable=false, length=45)
    private String platform_name;

    @Column(name="coin_cost", nullable=false)
    private double coin_cost;

    // Platform이 가지고 있는 작품들 리스트를 보관할 필요가 있을까?
    // 없지... 그냥 해당 플랫폼 들어가면 됨
}