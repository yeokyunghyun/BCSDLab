package com.example.demo.service;

import com.example.demo.controller.dto.request.BoardCreateRequest;
import com.example.demo.controller.dto.request.BoardUpdateRequest;
import com.example.demo.controller.dto.response.BoardResponse;
import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public BoardResponse createBoard(BoardCreateRequest request) {
        Board board = new Board(request.name());
        Board saved = boardRepository.insert(board);
        return BoardResponse.from(saved);
    }

    public List<BoardResponse> getBoards() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponse::from)
                .toList();
    }

    public BoardResponse getBoardById(Long id) {
        Board board;
        board = boardRepository.findById(id);
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardUpdateRequest request) {
        Board board = boardRepository.findById(id);
        board.update(request.name());
        Board updated = boardRepository.update(board);
        return BoardResponse.from(updated);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
