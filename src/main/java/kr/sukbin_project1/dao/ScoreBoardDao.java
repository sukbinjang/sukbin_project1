package kr.sukbin_project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreBoardDao {

    private String ADD_MATCH = "insert into scoreBoard (playerId, result, score1, score2) values (:playerId, :result, :score1, :score2)";
    private String PLAYER_MATCHES = "select result, score1, score2, matchDate from scoreBoard where playerId = ? order by matchDate desc limit ?,?";
    private String GET_MATCH = "select result, score1, score2, matchDate from scoreBoard where scoreBoardId = ?";
    public static final String COUNT_PLAYER_MATCHES = "select count(playerId) from scoreBoard where playerId=?";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<ScoreBoard> rowMapper = new BeanPropertyRowMapper<>(
            ScoreBoard.class);

    @Autowired
    public ScoreBoardDao(JdbcTemplate jdbcTemplate,
                         NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * 전적 수 세기
     */
    public Integer countMatches(int playerId) {
        return jdbcTemplate.queryForObject(COUNT_PLAYER_MATCHES, Integer.class, playerId);
    }

    /**
     * 전적 등록하기
     */
    public void addMatch(ScoreBoard scoreBoard) {
        namedParameterJdbcTemplate
                .update(ADD_MATCH, new BeanPropertySqlParameterSource(scoreBoard));
    }

    /**
     * 전적 목록보기
     */
    public List<ScoreBoard> listMatches(int playerId, int offset, int count) {
        return jdbcTemplate.query(PLAYER_MATCHES, rowMapper, playerId, offset, count);
    }

    public ScoreBoard getMatch(int scoreBoardId) {
        return jdbcTemplate.queryForObject(GET_MATCH, rowMapper, scoreBoardId);
    }
}