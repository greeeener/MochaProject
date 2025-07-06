package com.project.mocha.Genre.Entity;

import com.project.mocha.Keyword.Entity.Keyword;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "Genre")
public class Genre {
    @Id
    //@Column(nullable = false)
    @Column(name="genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "GENRE_SEQ", allocationSize=1)
    private int genreId;
    @Column(nullable = false, length = 50)
    private String genre_name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords = new ArrayList<>();
}
