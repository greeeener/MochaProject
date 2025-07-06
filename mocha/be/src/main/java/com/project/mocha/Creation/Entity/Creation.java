package com.project.mocha.Creation.Entity;

import com.project.mocha.Creator.Entity.Creator;
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
@AllArgsConstructor(access= AccessLevel.PROTECTED) //데이터 생성자(빈 컬럼 없게 채워줌)
//@EnableJpaAuditing //JPA Audit(시간값 자동으로 채워주는 기능) - DB 로그 관리
//@EntityListeners(AuditingEntityListener.class) //엔티티 변화 감지 - () 추가로 EnableJpaAuditing이 자동으로 갱신되도록
public class Creation { //implements Auditable { //데이터 생성 및 최근 수정일 자동 관리
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREATION_SEQ")
    @SequenceGenerator(name = "CREATION_SEQ", allocationSize=1)
    private int creation_id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 20)
    private String publisher;

    @Column(nullable = false)
    private int age_limit;

    @Column(nullable = false)
    private int episodes;

    @Column(nullable = false)
    private boolean is_end;

    @Column(nullable = false)
    private int free_episodes;

    @Column(nullable = false)
    private int gidamu;

    @Column(nullable = false, length = 10)
    private String period;

    @Column(nullable = false)
    private int rent_cost;

    @Column(nullable = false)
    private int buy_cost;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date latest_date;

    @Column(nullable = false)
    private String description;


    //@OneToMany(mappedBy = "Creation_has_Creator") private List<Creator> CreatorList;
    //@OneToMany(mappedBy = "Creation_has_Platform") private List<Platform> PlatformList;
    //@OneToMany(mappedBy = "Creation_has_Genre") private List<Genre> GenreList;
    //@OneToMany(mappedBy = "Creation_has_Keyword") private List<Keyword> KeywordList;

}
