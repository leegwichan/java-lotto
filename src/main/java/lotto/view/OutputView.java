package lotto.view;

import device.output.Output;
import lotto.lotto.dto.LottoDto;
import lotto.lotto.dto.LottoListDto;
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
}
