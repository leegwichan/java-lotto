package lotto.lotto.dto;

import lotto.lotto.Lotto;
import java.util.List;
import java.util.stream.Collectors;

public class LottoListDto {

    private List<LottoDto> lottoDtos;

    public LottoListDto(List<Lotto> lottos) {
        this.lottoDtos = lottos.stream()
                .map(lotto -> lotto.getLottoDto())
                .collect(Collectors.toList());
    }

    public List<LottoDto> getLottoDtos() {
        return lottoDtos;
    }
}
