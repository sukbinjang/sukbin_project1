package kr.sukbin_project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MatchServiceDao {

    private static final String ADD_MATCH_PLAYER = "insert into matchservice(playerId, playerTier) values(:playerId, :playerTier)";

    private static final String CHECK_MATCH = "select playerId from matchservice where playerTier = ?";

    private static final String DELETE_MATCH_PLAYER = "delete from matchservice where playerId = :playerId";

    private static final String PLAYER_MATCH_STATUS = "update player set onMatch= :onMatch where playerId= :playerId";


    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<MatchService> rowMapper = new BeanPropertyRowMapper<>(
            MatchService.class);

    private RowMapper<Player> rowMapper2 = new BeanPropertyRowMapper<>(
            Player.class);

    @Autowired
    public MatchServiceDao(JdbcTemplate jdbcTemplate,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * 매칭을 신청한 사용자를 추가
     */
    public void addPlayerMatchService(MatchService matchService) throws DuplicateKeyException {

        namedParameterJdbcTemplate.update(ADD_MATCH_PLAYER, new BeanPropertySqlParameterSource(matchService));

    }

    /**
     * 테이블에 있는 플레이어의 티어 확인
     */
    public List<MatchService> checkTier(int playerTier) {

        return jdbcTemplate.query(CHECK_MATCH, rowMapper, playerTier);

    }

    /**
     * 매칭된 플레이어를 테이블에서 삭제
     */
    public void deletePlayer(int playerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("playerId", playerId);
        namedParameterJdbcTemplate.update(DELETE_MATCH_PLAYER, params);
    }

    /**
     * 매칭여부에 따른 플레이어의 매칭상태 변경
     */
    public void updateStatus(String str, int playerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("onMatch", str);
        params.put("playerId", playerId);
        namedParameterJdbcTemplate.update(PLAYER_MATCH_STATUS, params);
    }

}
