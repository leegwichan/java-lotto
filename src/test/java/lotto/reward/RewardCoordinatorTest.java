package lotto.reward;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import lotto.lotto.Lotto;
import lotto.reward.dto.RewardDto;
import lotto.setting.LottoReward;
import lotto.winningnumber.WinningNumber;
import org.junit.jupiter.api.Test;
import java.util.List;

public class RewardCoordinatorTest {

    @Test
    void getRewardResultTest() {
        WinningNumber winningNumber = mock(WinningNumber.class);
        List<LottoReward> rewards = mockLottoRewards();
        List<Lotto> lottos = mockLottos();
        double exceptedRatio = 4000L / 2000 * 100;

        RewardDto result = new RewardCoordinator(winningNumber, rewards, 2000).getRewardDto(lottos);

        assertThat(result.getPriceRatio()).isEqualTo(exceptedRatio);
        assertThat(result.getRewardResult().size()).isEqualTo(1);
    }

    List<LottoReward> mockLottoRewards() {
        LottoReward mockLottoReward = mock(LottoReward.class);
        when(mockLottoReward.isSatisfyMatchingCondition(anyInt(), anyInt())).thenReturn(false, true, true);
        when(mockLottoReward.getPrize()).thenReturn(2000L);
        return List.of(mockLottoReward);
    }

    List<Lotto> mockLottos() {
        Lotto mockLotto = mock(Lotto.class);
        return List.of(mockLotto, mockLotto, mockLotto);
    }
}
