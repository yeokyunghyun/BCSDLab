package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleService.save(article);
        return ResponseEntity.ok(article);
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleDto> getArticlesWithMemberName() {
        return articleService.getArticleDto();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if(article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article findedArticle = articleService.findById(id);
        if(findedArticle == null) return ResponseEntity.notFound().build();
        articleService.update(article, findedArticle);
        articleService.save(id, findedArticle);
        return ResponseEntity.ok(findedArticle);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Article> deleteArticleById(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
