package com.gaplestrategist.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Domino> hand;
    private int score;
    private int gamesWon;
    
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
        this.gamesWon = 0;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<Domino> getHand() { return hand; }
    public void setHand(List<Domino> hand) { this.hand = hand; }
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public void addScore(int points) { this.score += points; }
    
    public int getGamesWon() { return gamesWon; }
    public void incrementGamesWon() { this.gamesWon++; }
    
    public void addDomino(Domino domino) {
        hand.add(domino);
    }
    
    public boolean removeDomino(Domino domino) {
        return hand.remove(domino);
    }
    
    public boolean hasWon() {
        return hand.isEmpty();
    }
    
    public int getHandValue() {
        int total = 0;
        for (Domino domino : hand) {
            total += domino.getGaplePoints();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return name + " - Score: " + score + " - Hand: " + hand.size() + " dominoes";
    }
}