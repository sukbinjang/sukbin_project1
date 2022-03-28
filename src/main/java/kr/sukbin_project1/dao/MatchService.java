package kr.sukbin_project1.dao;

import lombok.Data;

@Data
public class MatchService {
    int playerId;
    int playerTier;
    int rankingPoint;

    public MatchService(int playerId, int playerTier, int rankingPoint) {
        this.playerId = playerId;
        this.playerTier = playerTier;
        this.rankingPoint = rankingPoint;
    }

    public MatchService() {
    }
}
