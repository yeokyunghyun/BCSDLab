package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostsController {

    private final ArticleService articleService;

    public PostsController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getPosts(Model model) {
        List<ArticleDto> articleDtos = articleService.getArticleDto();
        model.addAttribute("articleDtos", articleDtos);
        return "posts";
    }

}
