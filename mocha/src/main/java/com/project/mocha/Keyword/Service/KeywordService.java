package com.project.mocha.Keyword.Service;

import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Genre.Repository.GenreRepository;
import com.project.mocha.Keyword.DTO.CreateKeywordRequest;
import com.project.mocha.Keyword.DTO.CreateKeywordResponse;
import com.project.mocha.Keyword.DTO.ReadKeywordResponse;
import com.project.mocha.Keyword.Entity.Keyword;
import com.project.mocha.Keyword.Repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final GenreRepository genreRepository;

    public CreateKeywordResponse createKeyword(CreateKeywordRequest request) {
        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Keyword keyword = Keyword.builder()
                .keyword_name(request.getKeywordName())
                .is_expose(request.getIsExpose())
                .genre(genre) // 연관된 Genre 설정
                .build();

        // DB에 저장
        keywordRepository.save(keyword);

        // 응답 객체 생성 및 반환
        return new CreateKeywordResponse(
                keyword.getKeyword_id(),
                keyword.getKeyword_name(),
                keyword.getIs_expose(),
                keyword.getGenre().getGenreId()
        );
    }

    public ReadKeywordResponse getKeywordList(int genreId) {
        // genreId를 기반으로 Keyword 리스트 조회
        List<Keyword> keywords = keywordRepository.findByGenre_GenreId(genreId);

        // 키워드 리스트를 ReadKeywordResponse 리스트로 변환
        List<ReadKeywordResponse.KeywordResponse> keywordResponses = keywords.stream()
                .map(keyword -> new ReadKeywordResponse.KeywordResponse(
                        keyword.getKeyword_id(),
                        keyword.getKeyword_name(),
                        keyword.getIs_expose(),
                        keyword.getGenre().getGenreId()
                ))
                .collect(Collectors.toList());

        // 반환할 ReadKeywordResponse 객체 생성
        return new ReadKeywordResponse(keywordResponses);
    }

}
