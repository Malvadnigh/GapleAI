package com.gaplestrategist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gaplestrategist.R;
import com.gaplestrategist.adapters.DominoValueAdapter;
import com.gaplestrategist.model.Domino;

import java.util.ArrayList;
import java.util.List;

public class RulesFragment extends Fragment {
    private RecyclerView rvDominoValues;
    private DominoValueAdapter dominoValueAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupDominoValuesRecyclerView();
    }

    private void initializeViews(View view) {
        rvDominoValues = view.findViewById(R.id.rv_domino_values);
    }

    private void setupDominoValuesRecyclerView() {
        List<Domino> dominoSet = generateDominoSet();
        dominoValueAdapter = new DominoValueAdapter(dominoSet);
        
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvDominoValues.setLayoutManager(layoutManager);
        rvDominoValues.setAdapter(dominoValueAdapter);
    }

    private List<Domino> generateDominoSet() {
        List<Domino> dominoes = new ArrayList<>();
        
        // Generate all domino combinations from 0-0 to 6-6
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                dominoes.add(new Domino(i, j));
            }
        }
        
        return dominoes;
    }
}