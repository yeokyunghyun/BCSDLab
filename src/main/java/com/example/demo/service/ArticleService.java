package com.example.demo.service;

import com.example.demo.domain.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {

    private HashMap<Long, Article> articles = new HashMap<>();
    private long id = 1;

    public void save(Article article) {
        articles.put(id++, article);
    }

    public void save(Long id, Article article) {
        articles.put(id, article);
    }
    public Article findById(Long id) {
        return articles.get(id);
    }
    public List<Article> getArticles() {
        // article들 모음
        List<Article> articleList = new ArrayList<>();
        for(long id : articles.keySet()) {
            articleList.add(articles.get(id));
        }
        return articleList;
    }
    public void deleteById(Long id) {
        articles.remove(id);
    }

    public void update(Article article, Article findedArticle) {
        findedArticle.setTitle(article.getTitle());
        findedArticle.setContent(article.getContent());
    }
}
