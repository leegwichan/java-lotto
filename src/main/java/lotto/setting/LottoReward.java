package lotto.setting;

import java.text.DecimalFormat;

public enum LottoReward {
    FIRST(6,0,2_000_000_000),
    SECOND(5,1,30_000_000),
    THIRD(5,0,1_500_000),
    FOURTH(4,0,50_000),
    FIFTH(3,0,5_000);

    private int countMatch;
    private int bonusMatch;
    private long prize;

    LottoReward(int countMatch, int bonusMatch, long prize) {
        this.countMatch = countMatch;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public boolean isSatisfyMatchingCondition(int currentMatch, int currentBonusMatch) {
        return currentMatch >= countMatch && currentBonusMatch >= bonusMatch;
    }

    public long getPrize() {
        return prize;
    }

    public int getCountMatch() {
        return countMatch;
    }

    public int getBonusMatch() {
        return bonusMatch;
    }
}
