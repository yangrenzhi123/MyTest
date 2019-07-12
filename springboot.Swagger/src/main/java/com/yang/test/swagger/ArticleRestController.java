package com.yang.test.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleRestController {

    @GetMapping(value = "/rest/article", produces = "application/json")
    public void saveArticle() {
    }
}