package com.example.demo.controller.dto.response;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;

import java.time.LocalDateTime;

public record ArticleResponse (
    Long id,
    String title,
    String content,
    String boardName,
    String author,
    LocalDateTime date
)
{
    public static ArticleResponse of(Article article, Member member, Board board) {
        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                board.getName(),
                member.getName(),
                article.getCreateDate());
    }
}
