package lotto;

import device.input.Input;
import device.output.Output;
import lotto.lotto.Lotto;
import lotto.lotto.LottoShop;
import lotto.reward.RewardCoordinator;
import lotto.setting.LottoApplicationSetting;
import lotto.view.InputView;
import lotto.winningnumber.WinningNumber;
import java.util.List;

public class LottoApplication {

    private final InputView inputView;
    private final Output output;
    private final LottoApplicationSetting setting;

    private Integer purchasePrice;
    private List<Lotto> lottos;

    LottoApplication(Input input, Output output, LottoApplicationSetting setting) {
        this.inputView = new InputView(input, output);
        this.output = output;
        this.setting = setting;
    }

    public void run() {
        try {
            runApplication();
        } catch (IllegalArgumentException exception) {
            output.print(exception.getMessage());
        }
    }

    private void runApplication() {
        buyLotto();
        showRewardResult();
    }

    private void buyLotto() {
        purchasePrice = inputView.readPurchasePrice();

        LottoShop lottoShop = setting.createLottoShop();
        lottos = lottoShop.buyLotto(purchasePrice);

        String lottosInfo = lottoShop.getLottoInfo(lottos);
        output.print(lottosInfo);
    }

    private void showRewardResult() {
        List<Integer> winningNumbers = inputView.readWinningNumbers();
        List<Integer> bonusNumbers = inputView.readBonusNumbers();

        WinningNumber winningNumber = setting.createWinningNumber(winningNumbers, bonusNumbers);
        RewardCoordinator rewardCoordinator = setting.createRewardCoordinator(winningNumber, purchasePrice);
        String rewardResult = rewardCoordinator.getRewardResult(lottos);
        output.print(rewardResult);
    }
}
