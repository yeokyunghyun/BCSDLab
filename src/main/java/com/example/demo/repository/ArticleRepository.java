package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ArticleRepository {

    private long id = 1;
    private HashMap<Long, Article> articles = new HashMap<>();

    public void save(Article article) {
        articles.put(id++, article);
    }

    public void save(Long id, Article article) {
        articles.put(id, article);
    }


    public Article get(Long id) {
        return articles.get(id);
    }

    public List<Article> getArticles() {
        List<Article> articleList = new ArrayList<>();
        for(long id : articles.keySet()) {
            articleList.add(articles.get(id));
        }
        return articleList;
    }

    public void remove(Long id) {
        articles.remove(id);
    }
}
