package com.gaplestrategist.model;

import java.util.Date;
import java.util.List;

public class GameHistory {
    private Date gameDate;
    private List<Player> finalScores;
    private String winner;
    private long duration;

    public GameHistory(Date gameDate, List<Player> finalScores) {
        this.gameDate = gameDate;
        this.finalScores = finalScores;
        this.winner = determineWinner();
        this.duration = 0;
    }

    public GameHistory(Date gameDate, List<Player> finalScores, long duration) {
        this.gameDate = gameDate;
        this.finalScores = finalScores;
        this.winner = determineWinner();
        this.duration = duration;
    }

    private String determineWinner() {
        if (finalScores == null || finalScores.isEmpty()) {
            return "No winner";
        }
        
        Player winner = finalScores.get(0);
        for (Player player : finalScores) {
            if (player.getScore() > winner.getScore()) {
                winner = player;
            }
        }
        
        return winner.getName();
    }

    public Date getGameDate() { return gameDate; }
    public List<Player> getFinalScores() { return finalScores; }
    public String getWinner() { return winner; }
    public long getDuration() { return duration; }

    public String getFormattedDuration() {
        if (duration <= 0) return "Unknown";
        long minutes = duration / 60000;
        long seconds = (duration % 60000) / 1000;
        if (minutes > 0) {
            return String.format("%d:%02d", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }
}