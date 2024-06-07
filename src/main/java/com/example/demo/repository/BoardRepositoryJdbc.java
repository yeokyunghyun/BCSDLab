package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class BoardRepositoryJdbc implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Board> boardRowMapper = (rs, rowNum) -> new Board(
            rs.getLong("id"),
            rs.getString("name")
    );

    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query("""
                SELECT id,name
                FROM board
                """, boardRowMapper);
    }



    @Override
    public Board findById(Long id) {
        return jdbcTemplate.queryForObject("""
                SELECT id,name
                FROM board
                WHERE id = ?
                """, boardRowMapper, id);
    }

    @Override
    public Board insert(Board board) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> { // con = connection
            PreparedStatement ps = con.prepareStatement("""
                    INSERT INTO board (name) VALUES(?) 
                    """, new String[]{"id"}); // new String[]{"id"} => 자동 생성된 키를 얻기위해 사용되는 열 이름으로 여기서는 이름이 id임
            ps.setString(1, board.getName()); // 첫번째 물음표에 할당될 인자
            return ps;
        }, keyHolder);
        return findById(keyHolder.getKey().longValue());
    }
    @Override
    public Board update(Board board) {
        return jdbcTemplate.queryForObject("""
                UPDATE board
                SET name = ?
                WHERE id = ?
                """, boardRowMapper, board.getName(), board.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("""
            DELETE FROM board
            WHERE id = ?
            """, id);
    }
}
