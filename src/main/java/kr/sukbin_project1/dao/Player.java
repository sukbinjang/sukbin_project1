package kr.sukbin_project1.dao;

import lombok.Data;

@Data
public class Player {
    private int playerId;
    private String email;
    private String password;
    private String name;
    private int tier;
    private int rankingPoint;
    private String address;
    private String phoneNum;
    private String playerLevel1;
    private String playerLevel2;
    private String playerLevel3;
    private String onMatch;

    /**
     * 회원가입한 플레이어의 티어를 세팅하기위해 만든 세터
     */
    public void setTier(String playerLevel1, String playerLevel2, String playerLevel3) {

        float c1 = 0, c2 = 0, c3 = 0;

        switch (playerLevel1) {
            case "실업선수출신":
                c1 = 5;
                break;
            case "대학선수출신":
                c1 = 4.5f;
                break;
            case "고등선수출신":
                c1 = 3.5f;
                break;
            case "중등선수출신":
                c1 = 3;
                break;
            case "초등선수출신":
                c1 = 2.5f;
                break;
            case "슈퍼동호인":
                c1 = 2.5f;
                break;
            case "비선수출신":
                break;
        }
        switch (playerLevel2) {
            case "5회이상":
                c2 = 5;
                break;
            case "3회이상":
                c2 = 4.5f;
                break;
            case "1회":
                c2 = 3;
                break;
            case "없음":
                break;
        }
        switch (playerLevel3) {
            case "5회이상":
                c3 = 5;
                break;
            case "3회이상":
                c3 = 4.5f;
                break;
            case "1회":
                c3 = 3;
                break;
            case "1회입상":
                c3 = 2;
                break;
            case "없음":
                break;
        }

        float checkTier = c1 + c2 + c3;

        if(10 <= checkTier){
            this.tier = 0;
        }
        else if(5 <= checkTier & checkTier < 10){
            this.tier = 1;
        }

        else if(2.5 <= checkTier & checkTier < 5){
            this.tier = 2;
        }

        else if(2 <= checkTier & checkTier < 2.5){
            this.tier = 3;
        }
        else{
            this.tier = 5;
        }

    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                ", rankingPoint=" + rankingPoint +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", playerLevel1='" + playerLevel1 + '\'' +
                ", playerLevel2='" + playerLevel2 + '\'' +
                ", playerLevel3='" + playerLevel3 + '\'' +
                ", onMatch='" + onMatch + '\'' +
                '}';
    }
}
