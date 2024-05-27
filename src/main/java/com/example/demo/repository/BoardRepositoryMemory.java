package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BoardRepositoryMemory implements BoardRepository{

    private static final HashMap<Long, Board> boards = new HashMap<>();
    private static final AtomicLong autoIncrement = new AtomicLong(1);

    static {
        boards.put(autoIncrement.getAndIncrement(), new Board("자유게시판"));
    }
    @Override
    public Board findById(Long id) {
        return boards.getOrDefault(id, null);
    }
}
