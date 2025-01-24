package com.project.mocha.Creation.Service;

import com.project.mocha.Creation.DTO.*;
import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Creation.Repository.CreationRepository;
import com.project.mocha.Creator.Entity.Creator;
import com.project.mocha.Creator.Repository.CreatorRepository;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Genre.Repository.GenreRepository;
import com.project.mocha.Keyword.Entity.Keyword;
import com.project.mocha.Keyword.Repository.KeywordRepository;
import com.project.mocha.Platform.Entity.Platform;
import com.project.mocha.Platform.Repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CreationService {
    private final CreationRepository creationRepository;
    private final CreatorRepository creatorRepository;
    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;
    private final KeywordRepository keywordRepository;
    public int createCreation(CreateCreationRequest request){
        List<Creator> creatorList = creatorRepository.findAllById(request.creatorList());
        List<Platform> platformList = platformRepository.findAllById(request.platformList());
        List<Genre> genreList = genreRepository.findAllById(request.genreList());
        List<Keyword> keywordList = keywordRepository.findAllById(request.keywordList());

        Creation newCreation = Creation.createCreation(request.title(), request.publisher(), request.age_limit(), request.episodes(), request.is_end(), request.free_episodes(), request.gidamu(), request.period(), request.rent_cost(), request.buy_cost(), request.start_date(), request.latest_date(), request.description(), creatorList, platformList, genreList, keywordList);

        creationRepository.save(newCreation);

        return  newCreation.getCreation_id();
    }

    public int updateCreation(int creationId, UpdateCreationRequest request){
        Creation creation = creationRepository.findById(creationId).orElseThrow();
        List<Creator> creatorList = creatorRepository.findAllById(request.creatorList());
        List<Platform> platformList = platformRepository.findAllById(request.platformList());
        List<Genre> genreList = genreRepository.findAllById(request.genreList());
        List<Keyword> keywordList = keywordRepository.findAllById(request.keywordList());

        creation.updateCreation(request.title(), request.publisher(), request.age_limit(), request.episodes(), request.is_end(), request.free_episodes(), request.gidamu(), request.period(), request.rent_cost(), request.buy_cost(), request.start_date(), request.latest_date(), request.description(), creatorList, platformList, genreList, keywordList);

        return creation.getCreation_id();
    }

    public CreationListResponse findAllWithFiltering(CreationListRequest request, Pageable pageable){
        List<Genre> genreList = genreRepository.findAllById(request.genreList());
        List<Keyword> keywordList = keywordRepository.findAllById(request.keywordList());
        Page<Creation> creations = creationRepository.findAllWithFiltering(request.searchKeyword(), request.publisher(), request.is_end(), genreList, keywordList, pageable);
        return CreationListResponse.from(creations);
    }

    public CreationResponse findCreation(int creationID){
        Creation creation = creationRepository.findById(creationID).orElseThrow();
        return  CreationResponse.from(creation);
    }
}
