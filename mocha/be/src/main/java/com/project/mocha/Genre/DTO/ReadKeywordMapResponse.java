package com.project.mocha.Genre.DTO;

import java.util.List;
import java.util.Map;

public record ReadKeywordMapResponse(
        Map<String, List<String>> keywords
){}
