package com.example.demo.controller;

import com.example.demo.controller.dto.response.ArticleResponse;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    private final ArticleService articleService;

    public PageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<ArticleResponse> articles = articleService.getAll();
        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("posts", articles);
        return "article";
    }

}
