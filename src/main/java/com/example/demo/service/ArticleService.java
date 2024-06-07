package com.example.demo.service;

import com.example.demo.controller.dto.response.ArticleResponse;
import com.example.demo.controller.dto.request.ArticleCreateRequest;
import com.example.demo.controller.dto.request.ArticleUpdateRequest;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public List<ArticleResponse> getAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> {
                    Member member = memberRepository.findById(article.getMemberId());
                    Board board = boardRepository.findById(article.getBoardId());
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }
    public List<ArticleResponse> getAllByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().
                filter(article -> article.getBoardId() == boardId)
                .map(article -> {
                    Member member = memberRepository.findById(article.getMemberId());
                    Board board = boardRepository.findById(article.getBoardId());
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    public ArticleResponse getById(Long id) {
        Article article = articleRepository.findById(id);
        Member member = memberRepository.findById(article.getMemberId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
        Article article = new Article(
                request.memberId(),
                request.boardId(),
                request.title(),
                request.content()
        );
        Article savedArticle = articleRepository.insert(article);
        Member member = memberRepository.findById(article.getMemberId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    @Transactional
    public ArticleResponse update(Long id, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(id);
        article.update(request.boardId(), request.title(), request.content());
        Article updatedArticle = articleRepository.update(id, article);

        Member member = memberRepository.findById(updatedArticle.getMemberId());
        Board board = boardRepository.findById(updatedArticle.getBoardId());

        return ArticleResponse.of(article, member, board);
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

}
