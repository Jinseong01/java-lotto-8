package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.enums.LottoRank;
import lotto.enums.OutputMessage;

public class OutputView {
    public void printLottoCount(int lottoCount) {
        System.out.println(String.format(OutputMessage.VIEW_LOTTO_COUNT.getMessage(), lottoCount));
    }

    public void printLottoNumber(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        lottos.forEach(lotto -> {
            sb.append(lotto.getNumbers()).append("\n");
        });
        System.out.println(sb);
    }

    public void printWinningResult(Map<LottoRank, Integer> prizeCounts) {
        StringBuilder sb = new StringBuilder();

        sb.append(OutputMessage.VIEW_WINNING_HEADER.getMessage()).append("\n");

        Arrays.stream(LottoRank.values()).forEach(lottoRank -> {
            int count = prizeCounts.get(lottoRank);
            sb.append(String.format(OutputMessage.VIEW_WINNING_RESULT.getMessage(), lottoRank.getDescription(),
                    String.format("%,d", lottoRank.getPrize()), count)).append("\n");
        });
        System.out.print(sb);
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println(String.format(OutputMessage.VIEW_RATE_OF_RETURN.getMessage(), rateOfReturn));
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("\n" + errorMessage);
    }
}
