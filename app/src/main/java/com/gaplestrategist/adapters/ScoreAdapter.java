package com.gaplestrategist.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gaplestrategist.R;
import com.gaplestrategist.model.Player;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private List<Player> players;
    private OnScoreChangeListener scoreChangeListener;

    public interface OnScoreChangeListener {
        void onScoreChanged(int position, int newScore);
    }

    public ScoreAdapter(List<Player> players, OnScoreChangeListener listener) {
        this.players = players;
        this.scoreChangeListener = listener;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Player player = players.get(position);
        holder.bind(player, position);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPlayerName;
        private EditText etScore;
        private TextView tvGamesWon;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.tv_player_name);
            etScore = itemView.findViewById(R.id.et_score);
            tvGamesWon = itemView.findViewById(R.id.tv_games_won);
        }

        public void bind(Player player, int position) {
            tvPlayerName.setText(player.getName());
            etScore.setText(String.valueOf(player.getScore()));
            tvGamesWon.setText("Wins: " + player.getGamesWon());

            etScore.setOnFocusChangeListener(null);
            etScore.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        int newScore = Integer.parseInt(s.toString());
                        if (scoreChangeListener != null) {
                            scoreChangeListener.onScoreChanged(position, newScore);
                        }
                    } catch (NumberFormatException e) {
                        // Invalid number, ignore
                    }
                }
            });
        }
    }
}