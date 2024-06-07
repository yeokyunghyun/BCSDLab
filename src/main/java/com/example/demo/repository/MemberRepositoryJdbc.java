package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class MemberRepositoryJdbc implements MemberRepository{

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> {
        Member member = new Member(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password")
        );
        return member;
    };

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("""
                    SELECT name, email, password
                    FROM member
                    """, memberRowMapper);
    }

    @Override
    public Member update(Member member) {
        return jdbcTemplate.queryForObject("""
                UPDATE member
                SET name=?,email=?,password=?,
                WHERE id=?
                """,
                memberRowMapper,
                member.getName(), member.getEmail(), member.getPassword(), member.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("""
            DELETE FROM member
            WHERE id = ?
            """, id);
    }

    @Override
    public Member findById(Long id) {
        return jdbcTemplate.queryForObject("""
                    SELECT id,name,email,password
                    FROM member
                    WHERE id = ?
                    """, memberRowMapper, id);
    }

    @Override
    public Member insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("""
            INSERT INTO name,email,password
            VALUES (?, ?, ?)
            FROM author
        """, new String[]{"id"});
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPassword());
            return ps;
        }, keyHolder);
        return findById(keyHolder.getKey().longValue());
    }

}
