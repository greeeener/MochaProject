package com.project.mocha.Keyword.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.mocha.Genre.Entity.Genre;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;
import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "keyword_id")
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
public class Keyword {
    @Id
    //@Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "KEYWORD_SEQ", allocationSize=1)
    private int keyword_id;

    @Column(nullable = false, length = 20)
    private String keyword_name;

    @Column(nullable = false, length = 20)
    private String is_expose;

    @ManyToOne(fetch = FetchType.LAZY) // Many-to-One 관계 설정
    @JoinColumn(name = "Genre_genre_id", nullable = false) // 외래 키 컬럼 이름 지정
    private Genre genre;

}
