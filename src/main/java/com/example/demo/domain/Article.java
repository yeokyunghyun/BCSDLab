package com.example.demo.domain;

import java.time.LocalDate;

public class Article {
    private Long id;
    private Long memberId;
    private Long boardId;

    private String title;
    private String content;
    private LocalDate publishDate;
    private LocalDate modifyDate;

    public Article() {}

    public Article(String title, String content, LocalDate publishDate, LocalDate modifyDate) {
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.modifyDate = modifyDate;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public LocalDate getPublishDate() {
        return LocalDate.now();
    }
    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
