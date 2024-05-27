package com.example.demo.controller.dto.request;

import java.time.LocalDateTime;

public record ArticleCreateRequest(
        Long memberId,
        Long boardId,
        String title,
        String content,
        LocalDateTime publishDate
) {

}
