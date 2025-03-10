package com.winrush;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Game extends AppCompatActivity implements GestureHelper.SwipeCallback {

    private int remainingAttempts = 3;
    private GifImageView leverView;
    private GifImageView firstRowView;
    private GifImageView secondRowView;
    private GifImageView thirdRowView;
    private GifImageView winningView;
    private View dragArea;
    private Handler animationHandler = new Handler();
    
    // Game components
    private SlotMachine slotMachine;
    private RewardManager rewardManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        // Initialize views
        initializeViews();
        
        // Initialize game components
        slotMachine = new SlotMachine(
            firstRowView, 
            secondRowView, 
            thirdRowView, 
            leverView, 
            getResources()
        );
        
        rewardManager = new RewardManager(this);
        
        // Setup lever animation
        slotMachine.setupLeverAnimation();
        
        // Setup swipe listener
        setupSwipeListener();
    }
    
    private void initializeViews() {
        leverView = findViewById(R.id.screenobj);
        firstRowView = findViewById(R.id.firstRow);
        secondRowView = findViewById(R.id.secondRow);
        thirdRowView = findViewById(R.id.thirdRow);
        winningView = findViewById(R.id.winningview);
        dragArea = findViewById(R.id.dragarea);
    }
    
    private void setupSwipeListener() {
        dragArea.setOnTouchListener(new GestureHelper.OnSwipeTouchListener(this, this));
    }
    
    public void resetGame() {
        winningView.setImageResource(R.drawable.win);
        winningView.setVisibility(View.INVISIBLE);
    }
    
    @Override
    public void onSwipeDown() {
        startGameRound();
    }
    
    @Override
    public void onSwipeLeft() {
        // Not used in this game
    }
    
    @Override
    public void onSwipeRight() {
        // Not used in this game
    }
    
    @Override
    public void onSwipeUp() {
        // Not used in this game
    }
    
    private void startGameRound() {
        try {
            // Disable drag area to prevent multiple swipes
            dragArea.setEnabled(false);
            
            // Setup animations
            slotMachine.setupSlotAnimations();
            
            // Create arrays to hold slot results
            final int[] slotResults = new int[3];
            
            // Play animation sequence and handle completion
            slotMachine.playAnimationSequence(slotResults, () -> processGameResults(slotResults));
            
        } catch (IOException ex) {
            System.out.println("Animation failed: " + ex.getMessage());
            dragArea.setEnabled(true);
        }
    }
    
    private void processGameResults(int[] slotResults) {
        RewardResult reward = rewardManager.calculateReward(slotResults);
        
        if (reward.getRewardType() != 0) {
            // Display win animation and save rewards
            showWinningAnimation(reward);
        } else {
            // Handle no win case
            handleTurnCompletion();
        }
    }
    
    private void showWinningAnimation(final RewardResult reward) {
        winningView.setVisibility(View.VISIBLE);
        
        animationHandler.postDelayed(() -> {
            // Update the UI with the winning image
            winningView.setImageResource(rewardManager.getWinningImageResource(reward.getRewardType()));
            
            // Save the reward to preferences
            rewardManager.saveReward(reward);
            
            // Schedule turn completion
            animationHandler.postDelayed(() -> handleTurnCompletion(), 1500);
        }, 1000);
    }
    
    private void handleTurnCompletion() {
        remainingAttempts--;
        
        if (remainingAttempts > 0) {
            Toast.makeText(getApplicationContext(), 
                    "Απομένουν " + remainingAttempts + " προσπάθειες", 
                    Toast.LENGTH_LONG).show();
            resetGame();
            dragArea.setEnabled(true);
        } else {
            Toast.makeText(getApplicationContext(), "Δοκίμασε ξανά άυριο", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Game.this, Home.class);
            startActivity(intent);
            finish();
        }
    }
}