package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleDto;
import com.example.demo.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private long id = 1;

    public ArticleService(MemberService memberService, ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.memberService = memberService;
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void save(Long id, Article article) {
        articleRepository.save(id, article);
    }
    public Article findById(Long id) {
        return articleRepository.get(id);
    }
    public void deleteById(Long id) {
        articleRepository.remove(id);
    }

    public void update(Article article, Article findedArticle) {
        findedArticle.setTitle(article.getTitle());
        findedArticle.setContent(article.getContent());
    }

    public List<Article> getArticles() {
        // article들 모음
        return articleRepository.getArticles();
    }
    public List<ArticleDto> getArticleDto() {
        List<Article> articles = articleRepository.getArticles();
        List<ArticleDto> articleDtos = new ArrayList<>();

        for(Article article : articles) {
            String memberName = memberService.getMemberById(article.getMemberId()).getName();

            articleDtos.add(new ArticleDto(article.getTitle(), memberName, article.getPublishDate(), article.getContent()));
        }
        return articleDtos;
    }
}
