package com.project.mocha.Keyword.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;
import java.util.List;


@Entity
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
public class Keyword {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KEYWORD_SEQ")
    @SequenceGenerator(name = "KEYWORD_SEQ", allocationSize=1)
    private int keyword_id;

    @Column(nullable = false, length = 20)
    private String keyword_name;

    @Column(nullable = false, length = 20)
    private String is_expose;

}
