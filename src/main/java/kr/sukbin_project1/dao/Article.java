package kr.sukbin_project1.dao;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Article {
  int articleId;
  String title;
  String content;
  int playerId;
  String name;
  String cdate;
  String udate;

  public String getTitleHtml() {
    return Encode.forHtml(title);
  }
  //35번글 XSS Filter, HTML DOM Tree글 부터 보기 (5:48~)

  public String getContentHtml() {
    return Encode.forHtml(content).replaceAll("\n", "<br>");
  }
}
