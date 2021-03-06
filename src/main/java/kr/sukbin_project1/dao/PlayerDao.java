package kr.sukbin_project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class PlayerDao {

    public static final String COUNT_PLAYERS = "select count(playerId) from player";
    public static final String COUNT_TIERPLAYERS = "select count(playerId) from player where tier = ?";
    public static final String UPDATE_PLAYER =
            "update player set email=:email, name=:name where playerId=:playerId";
    private static final String LIST_PLAYERS
            = "select playerId, email, name, rankingPoint from player order by playerId desc limit ?,?";
    private static final String RANK_PLAYERS
            = "select playerId, email, name, tier, rankingPoint from player where tier = ? order by rankingPoint desc limit ?, ?";
    private static final String ADD_PLAYER = """
            insert player(email, password, name, address, phoneNum, tier, playerLevel1, playerLevel2, playerLevel3)
            values(:email, sha2(:password,256), :name, :address, :phoneNum, :tier, :playerLevel1, :playerLevel2, :playerLevel3)""";
    private static final String LOGIN = """
            select playerId, email, name, tier, rankingPoint, playerLevel1, playerLevel2, playerLevel3, onMatch from player
            where (email, password) = (?, sha2(?,256))""";
    private static final String GET_PLAYER =
            "select playerId, email, name, tier, rankingPoint, playerLevel1, playerLevel2, playerLevel3, onMatch from player where playerId=?";
    private static final String UPDATE_PASSWORD = """
            update player set password=sha2(:newPassword,256)
            where playerId=:playerId and password=sha2(:password,256)""";
    private static final String GET_STATUS = "select onMatch from player where playerId = ?";
    private static final String SET_POINT = "update player set rankingPoint = :rankingPoint, tier = :tier where playerId = :playerId";
    private static final String SET_STATUS = "update player set onMatch = \"empty\" where playerId = :playerId";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Player> rowMapper = new BeanPropertyRowMapper<>(Player.class);

    @Autowired
    public PlayerDao(JdbcTemplate jdbcTemplate,
                     NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setRankPoint(Player player) {
        namedParameterJdbcTemplate.update(SET_POINT, new BeanPropertySqlParameterSource(player));
    }

    public String getStatus(int playerId) {
        return jdbcTemplate.queryForObject(GET_STATUS, String.class, playerId);
    }

    /**
     * ????????? ??????
     */
    public List<Player> listPlayers(int offset, int count) {
        return jdbcTemplate.query(LIST_PLAYERS, rowMapper, offset, count);
    }

    /**
     * ????????? ??????
     */
    public List<Player> listRankedPlayers(int tier, int offset, int count) {
        return jdbcTemplate.query(RANK_PLAYERS, rowMapper, tier, offset, count);
    }

    /**
     * ????????? ???
     */
    public Integer countPlayers() {
        return jdbcTemplate.queryForObject(COUNT_PLAYERS, Integer.class);
    }

    public int countTierPlayers(int tier) {
        return jdbcTemplate.queryForObject(COUNT_TIERPLAYERS, Integer.class, tier);
    }

    /**
     * ?????????
     *
     * @return ???????????? ????????? ??????
     * @throws EmptyResultDataAccessException ???????????? ????????? ??????
     */
    public Player login(String email, String password)
            throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(LOGIN, rowMapper, email, password);
    }

    /**
     * ????????? ??????
     */
    public Player getPlayer(int playerId) {
        return jdbcTemplate.queryForObject(GET_PLAYER, rowMapper, playerId);
    }

    /**
     * ????????? ??????
     *
     * @throws DuplicateKeyException ???????????? ???????????? ????????? ????????? ????????? ??????
     */
    public void addPlayer(Player player) throws DuplicateKeyException {
        namedParameterJdbcTemplate
                .update(ADD_PLAYER, new BeanPropertySqlParameterSource(player));
    }

    /**
     * ????????? ??????
     *
     * @throws DuplicateKeyException ???????????? ???????????? ????????? ????????? ????????? ??????
     */
    public void updatePlayer(Player player) throws DuplicateKeyException {
        namedParameterJdbcTemplate
                .update(UPDATE_PLAYER, new BeanPropertySqlParameterSource(player));
    }

    /**
     * ???????????? ??????
     *
     * @param playerId    ????????? ?????????
     * @param password    ?????? ????????????
     * @param newPassword ??? ????????????
     * @return ????????? ?????? ??????. 0??? ???????????? ??????????????? ?????? ????????? ?????? ??????
     */
    public int updatePassword(int playerId, String password, String newPassword) {
        Map<String, Object> params = new HashMap<>();
        params.put("playerId", playerId);
        params.put("password", password);
        params.put("newPassword", newPassword);
        return namedParameterJdbcTemplate.update(UPDATE_PASSWORD, params);
    }

    public void setStatus(Player player){
        namedParameterJdbcTemplate.update(SET_STATUS, new BeanPropertySqlParameterSource(player));
    }

}