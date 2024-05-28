package com.example.demo.controller;

import com.example.demo.controller.dto.response.ArticleResponse;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {

    private final ArticleService articleService;

    public PageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam(value = "boardId", required = false) Long boardId, Model model) {
        List<ArticleResponse> articles;
        if(boardId == null) {
            articles = articleService.getAll();
        }
        else {
            articles = articleService.getAllByBoardId(boardId);
        }

        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("posts", articles);
        return "article";
    }


}
