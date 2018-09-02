package com.yang.test.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleRestController {

    @RequestMapping(value = "/rest/article", produces = "application/json")
    public void saveArticle() {
    }
}