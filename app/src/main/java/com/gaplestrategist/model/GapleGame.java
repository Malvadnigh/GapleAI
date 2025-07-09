package com.gaplestrategist.model;

import java.util.*;

public class GapleGame {
    private List<Domino> allDominoes;
    private List<Domino> playerHand;
    private List<Domino> playedDominoes;
    private List<Player> players;
    private int currentPlayer;
    private int leftEnd;
    private int rightEnd;
    private boolean gameStarted;
    private boolean gameEnded;
    private Map<String, Integer> scores;
    
    public GapleGame(int numPlayers) {
        initializeGame(numPlayers);
    }
    
    private void initializeGame(int numPlayers) {
        allDominoes = generateDominoSet();
        playerHand = new ArrayList<>();
        playedDominoes = new ArrayList<>();
        players = new ArrayList<>();
        scores = new HashMap<>();
        
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
            scores.put("Player " + (i + 1), 0);
        }
        
        currentPlayer = 0;
        gameStarted = false;
        gameEnded = false;
        leftEnd = -1;
        rightEnd = -1;
    }
    
    private List<Domino> generateDominoSet() {
        List<Domino> dominoes = new ArrayList<>();
        // Standard domino set goes from 0-0 to 6-6
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                dominoes.add(new Domino(i, j));
            }
        }
        return dominoes;
    }
    
    public void dealHands() {
        Collections.shuffle(allDominoes);
        int dominoesPerPlayer = 7; // Standard gaple deal
        
        for (int i = 0; i < players.size(); i++) {
            List<Domino> hand = new ArrayList<>();
            for (int j = 0; j < dominoesPerPlayer; j++) {
                if (!allDominoes.isEmpty()) {
                    hand.add(allDominoes.remove(0));
                }
            }
            players.get(i).setHand(hand);
        }
    }
    
    public void startGame() {
        dealHands();
        gameStarted = true;
        
        // Find player with highest double to start
        int startingPlayer = findStartingPlayer();
        if (startingPlayer != -1) {
            currentPlayer = startingPlayer;
        }
    }
    
    private int findStartingPlayer() {
        int highestDouble = -1;
        int startingPlayer = -1;
        
        for (int i = 0; i < players.size(); i++) {
            for (Domino domino : players.get(i).getHand()) {
                if (domino.isDouble() && domino.getTotalValue() > highestDouble) {
                    highestDouble = domino.getTotalValue();
                    startingPlayer = i;
                }
            }
        }
        return startingPlayer;
    }
    
    public boolean canPlay(Domino domino) {
        if (!gameStarted) return false;
        if (playedDominoes.isEmpty()) return true;
        
        return domino.canConnect(leftEnd) || domino.canConnect(rightEnd);
    }
    
    public void playDomino(Domino domino, boolean onLeft) {
        if (!canPlay(domino)) return;
        
        playedDominoes.add(domino);
        
        if (playedDominoes.size() == 1) {
            // First domino played
            leftEnd = domino.getSide1();
            rightEnd = domino.getSide2();
        } else {
            if (onLeft) {
                if (domino.canConnect(leftEnd)) {
                    leftEnd = domino.getOtherSide(leftEnd);
                }
            } else {
                if (domino.canConnect(rightEnd)) {
                    rightEnd = domino.getOtherSide(rightEnd);
                }
            }
        }
        
        // Remove from current player's hand
        players.get(currentPlayer).getHand().remove(domino);
        
        // Check if player won
        if (players.get(currentPlayer).getHand().isEmpty()) {
            endGame();
            return;
        }
        
        // Next player
        currentPlayer = (currentPlayer + 1) % players.size();
    }
    
    public List<Domino> getPlayableDominoes(List<Domino> hand) {
        List<Domino> playable = new ArrayList<>();
        
        if (playedDominoes.isEmpty()) {
            return hand; // All dominoes playable at start
        }
        
        for (Domino domino : hand) {
            if (canPlay(domino)) {
                playable.add(domino);
            }
        }
        
        return playable;
    }
    
    public int calculateHandValue(List<Domino> hand) {
        int total = 0;
        for (Domino domino : hand) {
            total += domino.getGaplePoints();
        }
        return total;
    }
    
    public void endGame() {
        gameEnded = true;
        
        // Calculate final scores
        for (Player player : players) {
            int handValue = calculateHandValue(player.getHand());
            scores.put(player.getName(), handValue);
        }
    }
    
    public String getBestMove(List<Domino> hand) {
        List<Domino> playable = getPlayableDominoes(hand);
        
        if (playable.isEmpty()) {
            return "No moves available - pass turn";
        }
        
        // Strategy: Play highest value domino first, or doubles when possible
        Domino bestMove = playable.get(0);
        int bestScore = -1;
        
        for (Domino domino : playable) {
            int score = domino.getGaplePoints();
            if (domino.isDouble()) {
                score += 5; // Bonus for doubles
            }
            
            if (score > bestScore) {
                bestScore = score;
                bestMove = domino;
            }
        }
        
        return "Best move: " + bestMove + " (Value: " + bestScore + ")";
    }
    
    // Getters
    public List<Domino> getPlayerHand() { return playerHand; }
    public List<Domino> getPlayedDominoes() { return playedDominoes; }
    public int getLeftEnd() { return leftEnd; }
    public int getRightEnd() { return rightEnd; }
    public boolean isGameStarted() { return gameStarted; }
    public boolean isGameEnded() { return gameEnded; }
    public Map<String, Integer> getScores() { return scores; }
    public int getCurrentPlayer() { return currentPlayer; }
    public List<Player> getPlayers() { return players; }
}