package com.project.mocha.Creation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import com.project.mocha.Platform.Entity.Platform;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Date;
import java.util.List;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data //getter,setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "Creation")
//데이터 생성자(빈 컬럼 없게 채워줌)
//@EnableJpaAuditing //JPA Audit(시간값 자동으로 채워주는 기능) - DB 로그 관리
//@EntityListeners(AuditingEntityListener.class) //엔티티 변화 감지 - () 추가로 EnableJpaAuditing이 자동으로 갱신되도록
public class Creation { //implements Auditable { //데이터 생성 및 최근 수정일 자동 관리
    @Id
    @Column(name="creation_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int creationId;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Creation_has_Creator",
            joinColumns = @JoinColumn(name = "Creation_creation_id"),
            inverseJoinColumns = @JoinColumn(name = "Creator_creator_id")
    )
    private List<Creator> creatorList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Creation_has_Platform",
            joinColumns = @JoinColumn(name = "Creation_creation_id"),
            inverseJoinColumns = @JoinColumn(name = "Platform_platform_id")
    )
    private List<Platform> platformList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Creation_has_Genre",
            joinColumns = @JoinColumn(name = "Creation_creation_id"),
            inverseJoinColumns = @JoinColumn(name = "Genre_genre_id")
    )
    private List<Genre> genreList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Creation_has_Keyword",
            joinColumns = @JoinColumn(name = "Creation_creation_id"),
            inverseJoinColumns = @JoinColumn(name = "Keyword_keyword_id")
    )
    private List<Keyword> keywordList;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) private List<Creator> CreatorList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) private List<Platform> PlatformList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) private List<Genre> GenreList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) private List<Keyword> KeywordList;*/



    public Creation(String title, String publisher, int age_limit, int episodes, boolean is_end, int free_episodes, int gidamu, String period, int rent_cost, int buy_cost, Date start_date, Date latest_date, String description, List<Creator> creatorList, List<Platform> platformList, List<Genre> genreList, List<Keyword> keywordList) {
        this.title = title;
        this.publisher = publisher;
        this.age_limit = age_limit;
        this.episodes = episodes;
        this.is_end = is_end;
        this.free_episodes = free_episodes;
        this.gidamu = gidamu;
        this.period = period;
        this.rent_cost = rent_cost;
        this.buy_cost = buy_cost;
        this.start_date = start_date;
        this.latest_date = latest_date;
        this.description = description;
        this.creatorList = creatorList;
        this.platformList = platformList;
        this.genreList = genreList;
        this.keywordList = keywordList;
    }

    public static Creation createCreation(String title, String publisher, int age_limit, int episodes, boolean is_end, int free_episodes, int gidamu, String period, int rent_cost, int buy_cost, Date start_date, Date latest_date, String description, List<Creator> creatorList, List<Platform> platformList, List<Genre> genreList, List<Keyword> keywordList) {
        return new Creation(title, publisher, age_limit, episodes, is_end, free_episodes, gidamu, period, rent_cost, buy_cost, start_date, latest_date, description, creatorList, platformList, genreList, keywordList);
    }

    public void updateCreation(String title, String publisher, int age_limit, int episodes, boolean is_end, int free_episodes, int gidamu, String period, int rent_cost, int buy_cost, Date start_date, Date latest_date, String description, List<Creator> creatorList, List<Platform> platformList, List<Genre> genreList, List<Keyword> keywordList){
        this.title = title;
        this.publisher = publisher;
        this.age_limit = age_limit;
        this.episodes = episodes;
        this.is_end = is_end;
        this.free_episodes = free_episodes;
        this.gidamu = gidamu;
        this.period = period;
        this.rent_cost = rent_cost;
        this.buy_cost = buy_cost;
        this.start_date = start_date;
        this.latest_date = latest_date;
        this.description = description;
        this.creatorList = creatorList;
        this.platformList = platformList;
        this.genreList = genreList;
        this.keywordList = keywordList;
    }
}
