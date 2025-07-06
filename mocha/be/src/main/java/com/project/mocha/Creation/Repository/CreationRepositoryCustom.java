package com.project.mocha.Creation.Repository;

import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CreationRepositoryCustom {
    Page<Creation> findAllWithFiltering(String searchKeyword, String publisher, Boolean is_end, List<Genre> genreList, List<Keyword> keywordList, Pageable pageable);
}
