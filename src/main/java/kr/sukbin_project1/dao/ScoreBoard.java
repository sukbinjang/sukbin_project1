package kr.sukbin_project1.dao;

import lombok.Data;

@Data
public class ScoreBoard {

    private int scoreBoardId;
    private int playerId;
    private String result;
    private String score1;
    private String score2;
    private String matchDate;

    @Override
    public String toString() {
        return "ScoreBoard{" +
                "scoreBoardId=" + scoreBoardId +
                ", playerId=" + playerId +
                ", result='" + result + '\'' +
                ", score1='" + score1 + '\'' +
                ", score2='" + score2 + '\'' +
                ", matchDate='" + matchDate + '\'' +
                '}';
    }
}
