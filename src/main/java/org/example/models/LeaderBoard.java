package org.example.models;

import lombok.Builder;

import java.util.List;

@Builder
public class LeaderBoard {
    private int currentPossibleRank = 1;
    private List<PlayerRanking> playerRankings;

    public void incrementRank(){
        this.currentPossibleRank++;
    }
    public void addPlayerRankings(Player player) {
        PlayerRanking playerRanking = PlayerRanking.builder()
                .player(player)
                .rank(currentPossibleRank)
                .build();
        incrementRank();
        playerRankings.add(playerRanking);
    }

    public void display() {
        System.out.println("<<<<<< LEADERBOARD >>>>>>");
        for(PlayerRanking playerRanking : playerRankings) {
            System.out.println(playerRanking.getRank()+" "+playerRanking.getPlayer().getName());
        }
    }
}
