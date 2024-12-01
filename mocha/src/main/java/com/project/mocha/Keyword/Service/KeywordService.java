package com.project.mocha.Keyword.Service;

import com.project.mocha.Keyword.DTO.CreateKeywordRequest;
import com.project.mocha.Keyword.DTO.CreateKeywordResponse;
import com.project.mocha.Keyword.DTO.ReadKeywordListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KeywordService {

    public CreateKeywordResponse createKeyword(CreateKeywordRequest request) {
        return null;
    }

    public ReadKeywordListResponse getKeywordList(int id){
        return null;
    }
}
