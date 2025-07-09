package com.gaplestrategist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gaplestrategist.R;
import com.gaplestrategist.model.GameHistory;
import com.gaplestrategist.model.Player;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.HistoryViewHolder> {
    private List<GameHistory> gameHistoryList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());

    public GameHistoryAdapter(List<GameHistory> gameHistoryList) {
        this.gameHistoryList = gameHistoryList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        GameHistory gameHistory = gameHistoryList.get(position);
        holder.bind(gameHistory);
    }

    @Override
    public int getItemCount() {
        return gameHistoryList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGameDate;
        private TextView tvWinner;
        private TextView tvDuration;
        private TextView tvScores;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGameDate = itemView.findViewById(R.id.tv_game_date);
            tvWinner = itemView.findViewById(R.id.tv_winner);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvScores = itemView.findViewById(R.id.tv_scores);
        }

        public void bind(GameHistory gameHistory) {
            tvGameDate.setText(dateFormat.format(gameHistory.getGameDate()));
            tvWinner.setText("Winner: " + gameHistory.getWinner());
            tvDuration.setText("Duration: " + gameHistory.getFormattedDuration());
            
            // Build scores string
            StringBuilder scoresBuilder = new StringBuilder("Scores: ");
            List<Player> players = gameHistory.getFinalScores();
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                scoresBuilder.append(player.getName()).append(": ").append(player.getScore());
                if (i < players.size() - 1) {
                    scoresBuilder.append(", ");
                }
            }
            tvScores.setText(scoresBuilder.toString());
        }
    }
}