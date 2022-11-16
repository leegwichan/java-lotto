package lotto.setting;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lotto.Lotto;
import lotto.message.ExceptionMessage;
import java.util.List;

public enum LottoSetting {
    NORMAL(45, 6);

    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER;
    private final int NUMBER_OF_DRAWS;

    LottoSetting(int maxNumber, int numberOfDraws) {
        this.MAX_NUMBER = maxNumber;
        this.NUMBER_OF_DRAWS = numberOfDraws;
    }

    public void validate(List<Integer> numbers) {
        if (!isSizeMatched(numbers)) {
            throwException(ExceptionMessage.LOTTO_SIZE_NOT_MATCHED);
        }
        if (!isNumberInRange(numbers)) {
            throwException(ExceptionMessage.LOTTO_NUMBER_RANGE_OUT);
        }
        if (!isNotOverlapped(numbers)) {
            throwException(ExceptionMessage.LOTTO_NUMBER_OVERLAPPED);
        }
    }

    private boolean isSizeMatched(List<Integer> numbers) {
        return numbers.size() == NUMBER_OF_DRAWS;
    }

    private boolean isNumberInRange(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> MIN_NUMBER <= number && number <= MAX_NUMBER)
                .reduce(true, Boolean::logicalAnd);
    }

    private boolean isNotOverlapped(List<Integer> numbers) {
        return numbers.stream().distinct().count() == NUMBER_OF_DRAWS;
    }

    private void throwException(ExceptionMessage exceptionMessage) {
        throw new IllegalArgumentException(exceptionMessage.getMessage());
    }

    public Lotto createAutoLotto() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBER_OF_DRAWS);
        return new Lotto(randomNumbers,this);
    }
}
