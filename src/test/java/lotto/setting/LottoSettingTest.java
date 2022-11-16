package lotto.setting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.List;
import java.util.stream.Stream;

public class LottoSettingTest {

    @ParameterizedTest(name = "validateTest: Case {index}")
    @ArgumentsSource(ValidateTestNormalData.class)
    void validateTest_NormalCase(List<Integer> numbers) {
        LottoSetting lottoSetting = LottoSetting.NORMAL;

        assertThatCode(() -> {
            lottoSetting.validate(numbers);
        }).doesNotThrowAnyException();
    }

    static class ValidateTestNormalData implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(List.of(4,8,12,16,20,24)),
                    Arguments.of(List.of(5,9,12,13,44,45))
            );
        }
    }

    @ParameterizedTest(name = "validateTest: Abnormal Case {1}")
    @ArgumentsSource(ValidateTestAbnormalData.class)
    void validateTest_AbnormalCase(List<Integer> numbers, String testMessage) {
        LottoSetting lottoSetting = LottoSetting.NORMAL;

        assertThrows(IllegalArgumentException.class, () -> {
            lottoSetting.validate(numbers);
        });
    }

    static class ValidateTestAbnormalData implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(List.of(5,5,12,13,14,15), "Overlapped"),
                    Arguments.of(List.of(1,2,3), "list size not matched"),
                    Arguments.of(List.of(1,2,3,4,5,55), "over max number")
            );
        }
    }
}
