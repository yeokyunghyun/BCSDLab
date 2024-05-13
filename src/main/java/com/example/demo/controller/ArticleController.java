package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleService.save(article);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if(article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article findedArticle = articleService.findById(id);
        if(findedArticle == null) return ResponseEntity.notFound().build();
        findedArticle.setTitle(article.getTitle());
        findedArticle.setContent(article.getContent());
        articleService.save(id, findedArticle);
        return ResponseEntity.ok(findedArticle);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Article> deleteArticleById(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
