package com.example.demo.repository;

import com.example.demo.domain.Article;

import java.util.List;

public interface ArticleRepository {

    public List<Article> findAll();

    public Article findById(Long id);

    Article insert(Article request);

    Article update(Long id, Article article);

    void deleteById(Long id);
}
