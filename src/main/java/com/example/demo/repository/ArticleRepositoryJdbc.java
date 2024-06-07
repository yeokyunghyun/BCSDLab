package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@Primary
public class ArticleRepositoryJdbc implements ArticleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Article article = new Article(
                            rs.getLong("member_Id"),
                            rs.getLong("board_Id"),
                            rs.getString("title"),
                            rs.getString("content")
                    );
                    article.setId(rs.getLong("id"));
                    return article;
                }
        );
    }

    @Override
    public Article findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Article article = new Article(
                    rs.getLong("member_Id"),
                    rs.getLong("board_Id"),
                    rs.getString("title"),
                    rs.getString("content")
            );
            article.setId(rs.getLong("id"));
            return article;
        });
    }

    @Override
    @Transactional
    public Article insert(Article article) {
        String sql = "INSERT INTO article (member_id, board_id, title, content)" + "VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, article.getMemberId());
            ps.setLong(2, article.getBoardId());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getContent());
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        article.setId(generatedId);

        return article;
    }

    @Override
    public Article update(Long id, Article article) {
        String sql = "UPDATE article SET member_Id = ?, board_Id = ?, title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getMemberId(), article.getBoardId(), article.getTitle(), article.getContent(), id);
        return findById(id);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
