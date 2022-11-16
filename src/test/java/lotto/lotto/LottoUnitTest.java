package lotto.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import lotto.lotto.Lotto;
import lotto.setting.LottoSetting;
import org.junit.jupiter.api.Test;
import java.util.List;

public class LottoUnitTest {

    @Test
    void throwExceptionTest_whenFormNotMatched() {
        LottoSetting lottoSetting = mock(LottoSetting.class);
        doThrow(IllegalArgumentException.class).when(lottoSetting).validate(anyList());

        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(List.of(1,2,3,4,5,6), lottoSetting);
        });
    }

    @Test
    void notThrowExceptionTest_whenFormMatched() {
        LottoSetting lottoSetting = mock(LottoSetting.class);
        doNothing().when(lottoSetting).validate(anyList());

        assertThatCode(() -> {
            new Lotto(List.of(1,2,3,4,5,6), lottoSetting);
        }).doesNotThrowAnyException();
    }

    @Test
    void isInNumberTest_expectedTrue() {
        LottoSetting lottoSetting = mock(LottoSetting.class);
        Lotto lotto = new Lotto(List.of(6,5,4,3,2,1), lottoSetting);
        boolean excepted = true;

        boolean result = lotto.isInNumbers(4);

        assertThat(result).isEqualTo(excepted);
    }

    @Test
    void isInNumberTest_expectedFalse() {
        LottoSetting lottoSetting = mock(LottoSetting.class);
        Lotto lotto = new Lotto(List.of(6,5,4,3,2,1), lottoSetting);
        boolean excepted = false;

        boolean result = lotto.isInNumbers(7);

        assertThat(result).isEqualTo(excepted);
    }
}
