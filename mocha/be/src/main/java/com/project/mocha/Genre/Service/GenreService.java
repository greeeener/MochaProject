package com.project.mocha.Genre.Service;

import com.project.mocha.Genre.DTO.CreateGenreRequest;
import com.project.mocha.Genre.DTO.CreateGenreResponse;
import com.project.mocha.Genre.DTO.ReadGenreResponse;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Genre.Repository.GenreRepository;
import com.project.mocha.Keyword.Entity.Keyword;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    //장르 DB에 저장
    public CreateGenreResponse createGenre(CreateGenreRequest request){
        // Genre 엔티티 생성
        Genre genre = Genre.builder()
                .genre_name(request.getGenreName())
                .build();

        // DB에 저장
        genreRepository.save(genre);

        // 응답 객체 생성 및 반환
        return new CreateGenreResponse(
                genre.getGenre_id(),
                genre.getGenre_name()
        );
    }

    //장르 id(genre_id)로 찾기
    public ReadGenreResponse getGenre(int id){
        //id로 Creator Entity 검색
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));

        // ReadCreatorResponse로 변환하여 반환
        return new ReadGenreResponse(
                genre.getGenre_id(),
                genre.getGenre_name()
        );
    }

    //전체 장르 리스트 불러오기
    public List<ReadGenreResponse> getGenreList(){
        return genreRepository.findAll().stream()
                .map(genre -> new ReadGenreResponse(genre.getGenre_id(), genre.getGenre_name()))
                .toList();
    }

    // Genre와 이에 종속된 Keywords 한꺼번에 저장
    public CreateGenreResponse saveGenreWithKeywords(String genreName, List<CreateGenreRequest.KeywordRequestWithGenre> keywordRequests) {
        // Genre 엔티티 생성
        Genre genre = Genre.builder()
                .genre_name(genreName)
                .build();

        // Keywords와 연관 관계 설정
        List<Keyword> keywords = keywordRequests.stream()
                .map(request -> {
                    Keyword keyword = new Keyword();
                    keyword.setKeyword_name(request.keyword_name());
                    keyword.setIs_expose(request.is_expose());
                    keyword.setGenre(genre); // genre 필드 설정 추가
                    return keyword;
                })
                .collect(Collectors.toList());

        genre.setKeywords(keywords);

        // Genre 저장 (CascadeType.ALL로 인해 Keywords도 저장됨)
        genreRepository.save(genre);

        // 응답 객체 생성 및 반환
        return new CreateGenreResponse(
                genre.getGenre_id(),
                genre.getGenre_name()
        );
    }
}
