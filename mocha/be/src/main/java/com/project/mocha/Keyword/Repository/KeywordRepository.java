
package com.project.mocha.Keyword.Repository;

import com.project.mocha.Keyword.Entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    @Query("SELECT k FROM Keyword k WHERE k.genre.genre_id = :genreId")
    List<Keyword> findByGenreId(@Param("genreId") int genreId);
}