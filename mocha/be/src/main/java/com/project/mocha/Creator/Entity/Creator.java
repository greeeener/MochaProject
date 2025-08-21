package com.project.mocha.Creator.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "creator_id")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Creator")
public class Creator {
    @Id
    //@Column(nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@SequenceGenerator(name = "CREATOR_SEQ", allocationSize=1)
    private int creator_id;

    @Column(nullable = false, length = 50)
    private String creator_name;
}
