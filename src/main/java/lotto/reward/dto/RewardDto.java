package lotto.reward.dto;

import lotto.setting.LottoReward;
import java.util.LinkedHashMap;

public class RewardDto {

    private LinkedHashMap<LottoReward, Integer> rewardResult;
    private double priceRatio;

    public RewardDto(LinkedHashMap<LottoReward, Integer> rewardResult, double priceRatio){
        this.rewardResult = rewardResult;
        this.priceRatio = priceRatio;
    }

    public LinkedHashMap<LottoReward, Integer> getRewardResult() {
        return rewardResult;
    }

    public double getPriceRatio() {
        return priceRatio;
    }
}
