package com.example.demo.domain;

public class Board {
    private int id;
    private int name;

    public Board() {}
    public Board(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }
}
