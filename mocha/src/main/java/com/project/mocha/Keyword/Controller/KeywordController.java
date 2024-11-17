package com.project.mocha.Keyword.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mc/keyword")
@RequiredArgsConstructor
@Tag(name="keyword")
public class KeywordController {
}
