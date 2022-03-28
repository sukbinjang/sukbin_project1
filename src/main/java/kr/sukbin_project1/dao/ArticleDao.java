package kr.sukbin_project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleDao {

  public static final String COUNT_ARTICLES =
          "select count(articleId) from article";
  public static final String PLAYER_ARTICLES = """
      select articleId, title, playerId, name, left(cdate,16) cdate, left(udate,16) udate
      from article where playerId=? order by articleId desc limit ?,?""";
  public static final String COUNT_USER_ARTICLES =
          "select count(articleId) from article where playerId=?";
  private static final String LIST_ARTICLES = """
      select articleId, title, playerId, name, left(cdate,16) cdate, left(udate,16) udate
      from article order by articleId desc limit ?,?""";
  private static final String GET_ARTICLE = """
      select articleId, title, content, playerId, name,
        left(cdate,16) cdate, left(udate,16) udate
      from article where articleId=?""";

  private static final String ADD_ARTICLE = """
      insert article(title, content, playerId, name)
      values(:title, :content, :playerId, :name)""";

  private static final String UPDATE_ARTICLE = """
      update article set title=:title, content=:content
      where articleId=:articleId and playerId=:playerId""";

  private static final String DELETE_ARTICLE
          = "delete from article where articleId=:articleId and playerId=:playerId";

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private RowMapper<Article> rowMapper = new BeanPropertyRowMapper<>(
          Article.class);

  @Autowired
  public ArticleDao(JdbcTemplate jdbcTemplate,
                    NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  /**
   * 게시글 목록
   */
  public List<Article> listArticles(int offset, int count) {
    return jdbcTemplate.query(LIST_ARTICLES, rowMapper, offset, count);
  }

  /**
   * 게시글 건수
   */
  public Integer countArticles() {
    return jdbcTemplate.queryForObject(COUNT_ARTICLES, Integer.class);
  }

  /**
   * 나의 게시글
   */
  public List<Article> playerArticles(int playerId, int offset, int count) {
    return jdbcTemplate.query(PLAYER_ARTICLES, rowMapper, playerId, offset, count);
  }

  /**
   * 나의 게시글 건수
   */
  public Integer countUserArticles(int playerId) {
    return jdbcTemplate
            .queryForObject(COUNT_USER_ARTICLES, Integer.class, playerId);
  }

  /**
   * 게시글 조회
   */
  public Article getArticle(int articleId) {
    return jdbcTemplate.queryForObject(GET_ARTICLE, rowMapper, articleId);
  }

  /**
   * 게시글 등록
   */
  public void addArticle(Article article) {
    namedParameterJdbcTemplate
            .update(ADD_ARTICLE, new BeanPropertySqlParameterSource(article));
  }

  /**
   * 게시글 수정
   */
  public int updateArticle(Article article) {
    return namedParameterJdbcTemplate
            .update(UPDATE_ARTICLE, new BeanPropertySqlParameterSource(article));
  }

  /**
   * 게시글 삭제
   */
  public int deleteArticle(int articleId, int playerId) {
    Map<String, Object> params = new HashMap<>();
    params.put("articleId", articleId);
    params.put("userId", playerId);
    return namedParameterJdbcTemplate.update(DELETE_ARTICLE, params);
  }
}