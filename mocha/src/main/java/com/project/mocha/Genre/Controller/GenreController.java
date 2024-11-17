package com.project.mocha.Genre.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mc/genre")
@RequiredArgsConstructor
@Tag(name="genre")
public class GenreController {
}
