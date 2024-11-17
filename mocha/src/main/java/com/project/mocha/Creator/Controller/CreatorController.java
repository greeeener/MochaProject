package com.project.mocha.Creator.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mc/creator")
@RequiredArgsConstructor
@Tag(name="creator")
public class CreatorController {
}
