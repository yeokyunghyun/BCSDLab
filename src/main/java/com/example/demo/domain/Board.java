package com.example.demo.domain;

public class Board {
    Long id;
    String name;
    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Board(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void update(String name) {
        this.name = name;
    }
}
