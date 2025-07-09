package com.gaplestrategist.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;
import com.gaplestrategist.R;
import com.gaplestrategist.model.GapleGame;
import com.gaplestrategist.model.Domino;

import java.util.concurrent.ExecutionException;
import java.util.Arrays;
import java.util.List;

public class GameFragment extends Fragment {
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private PreviewView previewView;
    private TextView tvLeftEnd, tvRightEnd, tvStrategyAdvice;
    private Button btnNewGame, btnManualInput;
    private FloatingActionButton fabCapture;
    private GapleGame currentGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupClickListeners();
        initializeGame();
        
        if (allPermissionsGranted()) {
            startCamera();
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, CAMERA_REQUEST_CODE);
        }
    }

    private void initializeViews(View view) {
        previewView = view.findViewById(R.id.preview_view);
        tvLeftEnd = view.findViewById(R.id.tv_left_end);
        tvRightEnd = view.findViewById(R.id.tv_right_end);
        tvStrategyAdvice = view.findViewById(R.id.tv_strategy_advice);
        btnNewGame = view.findViewById(R.id.btn_new_game);
        btnManualInput = view.findViewById(R.id.btn_manual_input);
        fabCapture = view.findViewById(R.id.fab_capture);
    }

    private void setupClickListeners() {
        btnNewGame.setOnClickListener(v -> startNewGame());
        btnManualInput.setOnClickListener(v -> showManualInputDialog());
        fabCapture.setOnClickListener(v -> captureAndAnalyze());
    }

    private void initializeGame() {
        currentGame = new GapleGame(4); // Default 4 players
        updateGameDisplay();
    }

    private void startNewGame() {
        currentGame = new GapleGame(4);
        currentGame.startGame();
        updateGameDisplay();
        
        // Provide initial strategy advice
        if (!currentGame.getPlayers().isEmpty()) {
            List<Domino> playerHand = currentGame.getPlayers().get(0).getHand();
            String advice = currentGame.getBestMove(playerHand);
            tvStrategyAdvice.setText(advice);
        }
    }

    private void updateGameDisplay() {
        if (currentGame.isGameStarted()) {
            tvLeftEnd.setText("Left: " + currentGame.getLeftEnd());
            tvRightEnd.setText("Right: " + currentGame.getRightEnd());
            
            if (currentGame.isGameEnded()) {
                tvStrategyAdvice.setText("Game ended! Check scores for results.");
            } else {
                tvStrategyAdvice.setText("Game in progress. Use camera to analyze domino patterns.");
            }
        } else {
            tvLeftEnd.setText("Left: -");
            tvRightEnd.setText("Right: -");
            tvStrategyAdvice.setText("Start a new game to begin playing!");
        }
    }

    private void showManualInputDialog() {
        // TODO: Implement manual domino input dialog
        Toast.makeText(getContext(), "Manual input feature coming soon!", Toast.LENGTH_SHORT).show();
    }

    private void captureAndAnalyze() {
        // TODO: Implement image capture and domino recognition
        Toast.makeText(getContext(), "Domino recognition feature coming soon!", Toast.LENGTH_SHORT).show();
        
        // Simulate domino analysis for demo
        simulateDominoAnalysis();
    }

    private void simulateDominoAnalysis() {
        // Demo: Simulate finding a domino and providing strategy advice
        if (currentGame.isGameStarted() && !currentGame.isGameEnded()) {
            // Create a sample domino for analysis
            Domino sampleDomino = new Domino(3, 4);
            
            if (currentGame.canPlay(sampleDomino)) {
                tvStrategyAdvice.setText("Detected domino [3|4] - This is playable! " +
                        "Consider playing it on the " + 
                        (sampleDomino.canConnect(currentGame.getLeftEnd()) ? "left" : "right") + " end.");
            } else {
                tvStrategyAdvice.setText("Detected domino [3|4] - This domino cannot be played. " +
                        "Look for dominoes that connect to " + currentGame.getLeftEnd() + 
                        " or " + currentGame.getRightEnd());
            }
        }
    }

    private boolean allPermissionsGranted() {
        return Arrays.stream(REQUIRED_PERMISSIONS)
                .allMatch(permission -> ContextCompat.checkSelfPermission(
                        requireContext(), permission) == PackageManager.PERMISSION_GRANTED);
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = 
                ProcessCameraProvider.getInstance(requireContext());
        
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Log.e("GameFragment", "Error starting camera", e);
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(requireContext()), image -> {
            analyzeDominoFrame(image);
            image.close();
        });

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }

    private void analyzeDominoFrame(ImageProxy image) {
        // TODO: Implement actual domino recognition using ML
        // For now, just log the analysis
        Log.d("GapleAI", "Analyzing frame for domino patterns...");
        
        // Placeholder for future ML implementation
        // This would involve image processing to detect domino patterns
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(getContext(), R.string.camera_permission_required, Toast.LENGTH_SHORT).show();
            }
        }
    }
}