package lotto.reward;

import lotto.lotto.Lotto;
import lotto.reward.dto.RewardDto;
import lotto.setting.LottoReward;
import lotto.winningnumber.WinningNumber;
import java.util.LinkedHashMap;
import java.util.List;

public class RewardCoordinator {

    private final int NOT_REWARD = -1;

    private WinningNumber winningNumber;
    private List<LottoReward> rewards;
    private int purchasePrice;

    public RewardCoordinator(WinningNumber winningNumber,
                             List<LottoReward> rewards,
                             int purchasePrice) {
        this.winningNumber = winningNumber;
        this.rewards = rewards;
        this.purchasePrice = purchasePrice;
    }

    public RewardDto getRewardDto(List<Lotto> lottos) {
        int[] totalResult = new int[rewards.size()];
        long totalReward = 0;

        for (Lotto lotto : lottos) {
            int index = getRewardIndex(lotto);
            if (index == NOT_REWARD){
                continue;
            }

            totalResult[index]++;
            totalReward += rewards.get(index).getPrize();
        }
        return new RewardDto(getRewardResult(totalResult), getPriceRatio(totalReward));
    }

    private int getRewardIndex(Lotto lotto) {
        int currentMatch = winningNumber.countNumberMatched(lotto);
        int currentBonusMatch = winningNumber.countBonusNumberMatched(lotto);
        for (int index = 0; index < rewards.size(); index++) {
            LottoReward reward = rewards.get(index);
            if (reward.isSatisfyMatchingCondition(currentMatch, currentBonusMatch)) {
                return index;
            }
        }
        return NOT_REWARD;
    }

    private LinkedHashMap<LottoReward, Integer> getRewardResult(int[] totalResult) {
        LinkedHashMap<LottoReward, Integer> rewardResult = new LinkedHashMap<>();
        for (int index = rewards.size()-1; index >= 0; index--) {
            rewardResult.put(rewards.get(index), totalResult[index]);
        }
        return rewardResult;
    }

    private double getPriceRatio(long totalReward) {
        return (double) totalReward / purchasePrice * 100;
    }
}
