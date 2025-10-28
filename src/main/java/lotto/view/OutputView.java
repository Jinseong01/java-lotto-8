package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.enums.LottoRule;
import lotto.enums.OutputMessage;

public class OutputView {
    public void printLottoCount(int lottoCount) {
        System.out.println(String.format(OutputMessage.VIEW_LOTTO_COUNT.getMessage(), lottoCount));
    }

    public void printLottoNumber(List<List<Integer>> lottoNumbers) {
        lottoNumbers.forEach(lottoNumber -> {
            System.out.println(lottoNumber.toString());
        });
    }

    public void printWinningResult(Map<LottoRule, Integer> prizeCounts) {
        StringBuilder sb = new StringBuilder();

        sb.append(OutputMessage.VIEW_WINNING_HEADER.getMessage()).append("\n");

        Arrays.stream(LottoRule.values()).forEach(lottoRule -> {
            int count = prizeCounts.get(lottoRule);
            sb.append(String.format(OutputMessage.VIEW_WINNING_RESULT.getMessage(), lottoRule.getDescription(),
                    String.format("%,d", lottoRule.getPrize()), count)).append("\n");
        });
        System.out.print(sb);
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println(String.format(OutputMessage.VIEW_RATE_OF_RETURN.getMessage(), rateOfReturn));
    }

    public void printBlankLine() {
        System.out.println();
    }
}
