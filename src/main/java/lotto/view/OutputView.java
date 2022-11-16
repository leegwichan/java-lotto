package lotto.view;

import device.output.Output;
import lotto.lotto.dto.LottoDto;
import lotto.lotto.dto.LottoListDto;
import lotto.reward.dto.RewardDto;
import lotto.setting.LottoReward;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private final Output output;

    public OutputView(Output output) {
        this.output = output;
    }

    public void printLottos(LottoListDto lottoListDto) {
        List<LottoDto> lottoDtos = lottoListDto.getLottoDtos();

        output.print("\n" + lottoDtos.size() + "개를 구매했습니다.");
        for (LottoDto lottoDto : lottoDtos) {
            output.print(getNumbers(lottoDto));
        }
    }

    private String getNumbers(LottoDto lottoDto) {
        return lottoDto.getNumbers().stream()
                .sorted()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining(", ","[","]"));
    }

    public void printRewardResult(RewardDto rewardDto) {
        output.print("\n당첨 통계\n---");
        printRewardResult(rewardDto.getRewardResult());
        printPriceRatio(rewardDto.getPriceRatio());
    }

    private void printRewardResult(LinkedHashMap<LottoReward, Integer> rewardResult) {
        rewardResult.forEach(((lottoReward, count) -> {
            output.print(getLottoRewardInfo(lottoReward) + " - " + count + "개");
        }));
    }

    private String getLottoRewardInfo(LottoReward lottoReward) {
        return getCountMatchInfo(lottoReward.getCountMatch())
                + getBonusMatchInfo(lottoReward.getBonusMatch())
                + " " + getPrizeInfo(lottoReward.getPrize());
    }

    private String getCountMatchInfo(int countMatch) {
        return countMatch + "개 일치";
    }

    private String getBonusMatchInfo(int bonusMatch) {
        if (bonusMatch == 0) {
            return "";
        }
        if (bonusMatch == 1) {
            return ", 보너스 볼 일치";
        }
        return ", 보너스 볼 " + bonusMatch + "개 일치";
    }

    private String getPrizeInfo(long prize) {
        return "(" + new DecimalFormat("###,###").format(prize) + "원)";
    }

    private void printPriceRatio(double priceRatio) {
        output.print("총 수익률은 " + String.format("%.1f", priceRatio) + "%입니다.");
    }
}
