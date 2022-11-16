package lotto.view;

import device.input.Input;
import device.output.Output;
import lotto.message.InputRequestMessage;
import java.util.List;

public class InputView {

    private final Input input;
    private final Output output;

    public InputView(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public int readPurchasePrice() {
        return inputInteger(InputRequestMessage.PURCHASE_AMOUNT);
    }

    public List<Integer> readWinningNumbers() {
        return inputIntegerList(InputRequestMessage.WINNING_NUMBER);
    }

    public List<Integer> readBonusNumbers() {
        return inputIntegerList(InputRequestMessage.BONUS_NUMBER);
    }

    private Integer inputInteger(InputRequestMessage message) {
        output.print(message);
        return input.enterInteger();
    }

    private List<Integer> inputIntegerList(InputRequestMessage message) {
        output.print(message);
        return input.enterIntegerList();
    }
}
