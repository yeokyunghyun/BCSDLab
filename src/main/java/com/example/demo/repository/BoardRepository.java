package com.example.demo.repository;

import com.example.demo.controller.dto.response.BoardResponse;
import com.example.demo.domain.Board;

import java.util.List;

public interface BoardRepository {
    Board findById(Long id);
    Board insert(Board board);
    List<Board> findAll();
    Board update(Board board);

    void deleteById(Long id);
}
