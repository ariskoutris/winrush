package com.winrush;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class RewardManager {
    private final Context context;
    
    public RewardManager(Context context) {
        this.context = context;
    }
    
    public RewardResult calculateReward(int[] slots) {
        if (slots[0] == slots[1] && slots[0] == slots[2]) {
            return new RewardResult(slots[0], 3); // All three match
        } else if (slots[0] == slots[1] || slots[0] == slots[2]) {
            return new RewardResult(slots[0], 1); // First matches with second or third
        } else if (slots[1] == slots[2]) {
            return new RewardResult(slots[1], 1); // Second matches with third
        }
        return new RewardResult(0, 0); // No matches
    }
    
    public void saveReward(RewardResult reward) {
        SharedPreferences.Editor editor = context.getSharedPreferences("savefile", MODE_PRIVATE).edit();
        SharedPreferences reader = context.getSharedPreferences("savefile", MODE_PRIVATE);
        
        String rewardKey = getRewardKey(reward.getRewardType());
        if (!rewardKey.isEmpty()) {
            int previousCount = reader.getInt(rewardKey, 0);
            editor.putInt(rewardKey, previousCount + reward.getCount());
            editor.apply();
        }
    }
    
    public String getRewardKey(int rewardType) {
        switch (rewardType) {
            case 1: return "simply";
            case 2: return "mikel";
            case 3: return "efood";
            case 4: return "two";
            case 5: return "fivehundred";
            case 6: return "seven";
            case 7: return "odeon";
            case 8: return "dominos";
            case 9: return "bluestar";
            default: return "";
        }
    }
    
    public int getWinningImageResource(int rewardType) {
        switch (rewardType) {
            case 1: return R.drawable.simply;
            case 2: return R.drawable.mikel;
            case 3: return R.drawable.efood;
            case 4: return R.drawable.two;
            case 5: return R.drawable.fivehundred;
            case 6: return R.drawable.seven;
            case 7: return R.drawable.odeon;
            case 8: return R.drawable.dominos;
            case 9: return R.drawable.bluestar;
            default: return R.drawable.win;
        }
    }
}