package com.example.demo.repository;

import com.example.demo.domain.Article;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepositoryMemory implements ArticleRepository{

    private static final HashMap<Long, Article> articles = new HashMap<>();
    private static final AtomicLong autoIncrement = new AtomicLong(1);
    public List<Article> findAll() {
        return articles.entrySet().stream()
                .map(it -> {
                    Article article = it.getValue();
                    article.setId(it.getKey());
                    return article;
                }).toList();
    }

    @Override
    public Article findById(Long id) {
        return articles.getOrDefault(id, null);
    }

    @Override
    public Article insert(Article article) {
        long id = autoIncrement.getAndIncrement();
        articles.put(id, article);
        article.setId(id);
        return article;
    }

    @Override
    public Article update(Long id, Article article) {
        articles.put(id, article);
        article.setId(id);
        return article;
    }

    @Override
    public void deleteById(Long id) {
        articles.remove(id);
    }
}
