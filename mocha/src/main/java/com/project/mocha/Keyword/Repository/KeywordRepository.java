package com.project.mocha.Keyword.Repository;

import com.project.mocha.Keyword.Entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    List<Keyword> findByGenre_GenreId(int genreId);
}
