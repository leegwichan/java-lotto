package lotto.lotto;

import lotto.lotto.dto.LottoDto;
import lotto.setting.LottoSetting;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this(numbers, LottoSetting.NORMAL);
    }

    public Lotto(List<Integer> numbers, LottoSetting setting) {
        setting.validate(numbers);
        this.numbers = numbers;
    }

    public LottoDto getLottoDto() {
        return new LottoDto(List.copyOf(numbers));
    }

    public boolean isInNumbers(int number) {
        return numbers.stream()
                .map(lottoNumber -> lottoNumber == number)
                .reduce(false, Boolean::logicalOr);
    }
}
