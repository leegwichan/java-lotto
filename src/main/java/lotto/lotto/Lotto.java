package lotto.lotto;

import lotto.message.ExceptionMessage;
import lotto.setting.LottoSetting;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this(numbers, LottoSetting.NORMAL);
    }

    public Lotto(List<Integer> numbers, LottoSetting setting) {
        setting.validate(numbers);
        this.numbers = numbers;
    }

    public String getNumbers() {
        return numbers.stream().sorted()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining(", ","[","]"));
    }

    public boolean isInNumbers(int number) {
        return numbers.stream()
                .map(lottoNumber -> lottoNumber == number)
                .reduce(false, Boolean::logicalOr);
    }
}
