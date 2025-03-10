package com.winrush;

public class RewardResult {
    private final int rewardType;
    private final int count;
    
    public RewardResult(int rewardType, int count) {
        this.rewardType = rewardType;
        this.count = count;
    }
    
    public int getRewardType() {
        return rewardType;
    }
    
    public int getCount() {
        return count;
    }
}
