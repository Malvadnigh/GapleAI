package com.gaplestrategist.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gaplestrategist.R;
import com.gaplestrategist.adapters.ScoreAdapter;
import com.gaplestrategist.adapters.GameHistoryAdapter;
import com.gaplestrategist.model.Player;
import com.gaplestrategist.model.GameHistory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.lang.reflect.Type;

public class ScoreFragment extends Fragment {
    private RecyclerView rvCurrentScores, rvGameHistory;
    private Button btnAddPlayer, btnResetScores, btnClearHistory;
    private ScoreAdapter scoreAdapter;
    private GameHistoryAdapter historyAdapter;
    private List<Player> players;
    private List<GameHistory> gameHistoryList;
    private SharedPreferences sharedPrefs;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupRecyclerViews();
        setupClickListeners();
        loadData();
    }

    private void initializeViews(View view) {
        rvCurrentScores = view.findViewById(R.id.rv_current_scores);
        rvGameHistory = view.findViewById(R.id.rv_game_history);
        btnAddPlayer = view.findViewById(R.id.btn_add_player);
        btnResetScores = view.findViewById(R.id.btn_reset_scores);
        btnClearHistory = view.findViewById(R.id.btn_clear_history);
        
        sharedPrefs = requireContext().getSharedPreferences("gaple_prefs", Context.MODE_PRIVATE);
        gson = new Gson();
        
        players = new ArrayList<>();
        gameHistoryList = new ArrayList<>();
    }

    private void setupRecyclerViews() {
        // Current scores RecyclerView
        scoreAdapter = new ScoreAdapter(players, this::onPlayerScoreChanged);
        rvCurrentScores.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCurrentScores.setAdapter(scoreAdapter);
        
        // Game history RecyclerView
        historyAdapter = new GameHistoryAdapter(gameHistoryList);
        rvGameHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGameHistory.setAdapter(historyAdapter);
    }

    private void setupClickListeners() {
        btnAddPlayer.setOnClickListener(v -> addNewPlayer());
        btnResetScores.setOnClickListener(v -> resetAllScores());
        btnClearHistory.setOnClickListener(v -> clearGameHistory());
    }

    private void addNewPlayer() {
        String playerName = "Player " + (players.size() + 1);
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);
        scoreAdapter.notifyItemInserted(players.size() - 1);
        savePlayersData();
    }

    private void resetAllScores() {
        // Save current game to history before resetting
        if (!players.isEmpty()) {
            GameHistory gameHistory = new GameHistory(new Date(), new ArrayList<>(players));
            gameHistoryList.add(0, gameHistory);
            historyAdapter.notifyItemInserted(0);
            saveGameHistory();
        }
        
        // Reset all player scores
        for (Player player : players) {
            player.setScore(0);
        }
        scoreAdapter.notifyDataSetChanged();
        savePlayersData();
        
        Toast.makeText(getContext(), "Scores reset and game saved to history", Toast.LENGTH_SHORT).show();
    }

    private void clearGameHistory() {
        gameHistoryList.clear();
        historyAdapter.notifyDataSetChanged();
        saveGameHistory();
        Toast.makeText(getContext(), "Game history cleared", Toast.LENGTH_SHORT).show();
    }

    private void onPlayerScoreChanged(int position, int newScore) {
        if (position >= 0 && position < players.size()) {
            players.get(position).setScore(newScore);
            savePlayersData();
        }
    }

    private void loadData() {
        // Load players
        String playersJson = sharedPrefs.getString("players", null);
        if (playersJson != null) {
            Type playersType = new TypeToken<List<Player>>(){}.getType();
            List<Player> loadedPlayers = gson.fromJson(playersJson, playersType);
            if (loadedPlayers != null) {
                players.clear();
                players.addAll(loadedPlayers);
                scoreAdapter.notifyDataSetChanged();
            }
        }
        
        // If no players, add default ones
        if (players.isEmpty()) {
            for (int i = 1; i <= 4; i++) {
                players.add(new Player("Player " + i));
            }
            scoreAdapter.notifyDataSetChanged();
        }
        
        // Load game history
        String historyJson = sharedPrefs.getString("game_history", null);
        if (historyJson != null) {
            Type historyType = new TypeToken<List<GameHistory>>(){}.getType();
            List<GameHistory> loadedHistory = gson.fromJson(historyJson, historyType);
            if (loadedHistory != null) {
                gameHistoryList.clear();
                gameHistoryList.addAll(loadedHistory);
                historyAdapter.notifyDataSetChanged();
            }
        }
    }

    private void savePlayersData() {
        String playersJson = gson.toJson(players);
        sharedPrefs.edit().putString("players", playersJson).apply();
    }

    private void saveGameHistory() {
        String historyJson = gson.toJson(gameHistoryList);
        sharedPrefs.edit().putString("game_history", historyJson).apply();
    }
}