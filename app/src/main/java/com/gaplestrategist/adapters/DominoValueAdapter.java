package com.gaplestrategist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gaplestrategist.R;
import com.gaplestrategist.model.Domino;

import java.util.List;

public class DominoValueAdapter extends RecyclerView.Adapter<DominoValueAdapter.DominoViewHolder> {
    private List<Domino> dominoList;

    public DominoValueAdapter(List<Domino> dominoList) {
        this.dominoList = dominoList;
    }

    @NonNull
    @Override
    public DominoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_domino_value, parent, false);
        return new DominoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DominoViewHolder holder, int position) {
        Domino domino = dominoList.get(position);
        holder.bind(domino);
    }

    @Override
    public int getItemCount() {
        return dominoList.size();
    }

    class DominoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDominoDisplay;
        private TextView tvDominoValue;
        private TextView tvGaplePoints;

        public DominoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDominoDisplay = itemView.findViewById(R.id.tv_domino_display);
            tvDominoValue = itemView.findViewById(R.id.tv_domino_value);
            tvGaplePoints = itemView.findViewById(R.id.tv_gaple_points);
        }

        public void bind(Domino domino) {
            tvDominoDisplay.setText(domino.toString());
            tvDominoValue.setText("Value: " + domino.getTotalValue());
            tvGaplePoints.setText("Gaple: " + domino.getGaplePoints());

            // Set background color based on domino type
            if (domino.isDouble()) {
                itemView.setBackgroundColor(itemView.getContext().getColor(R.color.domino_double_bg));
            } else if (domino.getTotalValue() >= 10) {
                itemView.setBackgroundColor(itemView.getContext().getColor(R.color.domino_high_value));
            } else {
                itemView.setBackgroundColor(itemView.getContext().getColor(R.color.domino_low_value));
            }
        }
    }
}