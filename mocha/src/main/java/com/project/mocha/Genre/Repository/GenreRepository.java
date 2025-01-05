package com.project.mocha.Genre.Repository;

import com.project.mocha.Genre.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
