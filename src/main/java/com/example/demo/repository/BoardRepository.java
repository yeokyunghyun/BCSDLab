package com.example.demo.repository;

import com.example.demo.domain.Board;

public interface BoardRepository {
    public Board findById(Long id);
}
