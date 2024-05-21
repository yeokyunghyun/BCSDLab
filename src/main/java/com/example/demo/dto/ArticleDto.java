package com.example.demo.dto;

import java.time.LocalDate;

public class ArticleDto {
    private String title;
    private String memberName;
    private LocalDate publishDate;
    private String content;

    public ArticleDto(String title, String memberName, LocalDate publishDate, String content) {
        this.title = title;
        this.memberName = memberName;
        this.publishDate = publishDate;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getMemberName() {
        return memberName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getContent() {
        return content;
    }
}
