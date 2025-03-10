package com.winrush.utils;

import android.content.Context;
import android.content.SharedPreferences;


// Utility class to handle shared preferences operations
public class PreferenceUtils {
    private static final String PREFS_NAME = "savefile";
    
    // Reward keys
    public static final String KEY_SIMPLY = "simply";
    public static final String KEY_MIKEL = "mikel";
    public static final String KEY_EFOOD = "efood";
    public static final String KEY_TWO = "two";
    public static final String KEY_FIVEHUNDRED = "fivehundred";
    public static final String KEY_SEVEN = "seven";
    public static final String KEY_ODEON = "odeon";
    public static final String KEY_DOMINOS = "dominos";
    public static final String KEY_BLUESTAR = "bluestar";
    
    // Gets the reward count for the specified reward type
    public static int getRewardCount(Context context, String rewardKey) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(rewardKey, 0);
    }
    
    // Increments the reward count for the specified reward type
    public static void addReward(Context context, String rewardKey, int count) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int currentCount = prefs.getInt(rewardKey, 0);
        prefs.edit().putInt(rewardKey, currentCount + count).apply();
    }
    
    // Gets the reward key from a reward type identifier
    public static String getRewardKeyFromType(int rewardType) {
        switch (rewardType) {
            case 1: return KEY_SIMPLY;
            case 2: return KEY_MIKEL;
            case 3: return KEY_EFOOD;
            case 4: return KEY_TWO;
            case 5: return KEY_FIVEHUNDRED;
            case 6: return KEY_SEVEN;
            case 7: return KEY_ODEON;
            case 8: return KEY_DOMINOS;
            case 9: return KEY_BLUESTAR;
            default: return "";
        }
    }
}