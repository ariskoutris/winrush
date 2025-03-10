package com.winrush;

import android.content.res.Resources;
import android.os.Handler;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SlotMachine {
    private final GifImageView firstRowView;
    private final GifImageView secondRowView;
    private final GifImageView thirdRowView;
    private final GifImageView leverView;
    private final Resources resources;
    private final Handler animationHandler = new Handler();
    
    public SlotMachine(GifImageView firstRowView, GifImageView secondRowView, 
                       GifImageView thirdRowView, GifImageView leverView, 
                       Resources resources) {
        this.firstRowView = firstRowView;
        this.secondRowView = secondRowView;
        this.thirdRowView = thirdRowView;
        this.leverView = leverView;
        this.resources = resources;
    }
    
    public int setWinner(GifImageView slotView) {
        final int r = new Random().nextInt(25);
        if (r <= 3) {
            slotView.setImageResource(R.drawable.slotendingsimply);
            return 1;
        } else if (r <= 7) {
            slotView.setImageResource(R.drawable.slotendingmikel);
            return 2;
        } else if (r <= 11) {
            slotView.setImageResource(R.drawable.slotendingefood);
            return 3;
        } else if (r <= 14) {
            slotView.setImageResource(R.drawable.slotending2);
            return 4;
        } else if (r <= 17) {
            slotView.setImageResource(R.drawable.slotending500);
            return 5;
        } else if (r <= 20) {
            slotView.setImageResource(R.drawable.slotending7);
            return 6;
        } else if (r <= 22) {
            slotView.setImageResource(R.drawable.slotendingodeon);
            return 7;
        } else if (r <= 24) {
            slotView.setImageResource(R.drawable.slotendingdominos);
            return 8;
        } else {
            slotView.setImageResource(R.drawable.slotendingbluestar);
            return 9;
        }
    }
    
    public void setupLeverAnimation() {
        try {
            GifDrawable gifFromResource = new GifDrawable(resources, R.drawable.leveranimation);
            ((GifDrawable)leverView.getDrawable()).stop();
        } catch(IOException ex) {
            System.out.println("Gif initialization failed: " + ex.getMessage());
        }
    }
    
    public void setupSlotAnimations() throws IOException {
        // Setup lever and slot animations
        leverView.setImageResource(R.drawable.leveranimation);
        firstRowView.setImageResource(R.drawable.spinanimation);
        secondRowView.setImageResource(R.drawable.spinanimation);
        thirdRowView.setImageResource(R.drawable.spinanimation);
        
        // Reset and stop the lever animation
        GifDrawable leverGif = new GifDrawable(resources, R.drawable.leveranimation);
        leverGif.start();
        ((GifDrawable) leverView.getDrawable()).reset();
        ((GifDrawable) leverView.getDrawable()).stop();
    }
    
    public void playAnimationSequence(final int[] slotResults, final Runnable onCompletion) throws IOException {
        final GifDrawable firstSpin = new GifDrawable(resources, R.drawable.spinanimation);
        final GifDrawable secondSpin = new GifDrawable(resources, R.drawable.spinanimation);
        final GifDrawable thirdSpin = new GifDrawable(resources, R.drawable.spinanimation);
        
        // Start slot animations with delays
        animationHandler.postDelayed(() -> {
            firstSpin.start();
            secondSpin.seekToFrame(15);
            secondSpin.start();
            thirdSpin.seekToFrame(25);
            thirdSpin.start();
        }, 1000);
        
        // Set winners with delays
        animationHandler.postDelayed(() -> slotResults[0] = setWinner(firstRowView), 2000);
        animationHandler.postDelayed(() -> slotResults[1] = setWinner(secondRowView), 2500);
        animationHandler.postDelayed(() -> slotResults[2] = setWinner(thirdRowView), 3000);
        
        // Process results after all animations complete
        animationHandler.postDelayed(onCompletion, 5000);
    }
}