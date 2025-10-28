package lotto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.enums.LottoRule;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int price = Integer.parseInt(inputView.readPurchasePrice());
        outputView.printBlankLine();

        outputView.printLottoCount(price / 1000);

        List<List<Integer>> lottoNumbers = new ArrayList<>(
                List.of(
                        List.of(8, 21, 23, 41, 42, 43),
                        List.of(3, 5, 11, 16, 32, 38),
                        List.of(7, 11, 16, 35, 36, 44),
                        List.of(1, 8, 11, 31, 41, 42),
                        List.of(13, 14, 16, 38, 42, 45),
                        List.of(7, 11, 30, 40, 42, 43),
                        List.of(2, 13, 22, 32, 38, 45),
                        List.of(1, 3, 5, 14, 22, 45)
                )
        );
        outputView.printLottoNumber(lottoNumbers);
        outputView.printBlankLine();

        String winningNumber = inputView.readWinningNumber();
        outputView.printBlankLine();

        String bonusNumber = inputView.readBounusNumber();
        outputView.printBlankLine();

        Map<LottoRule, Integer> result = new EnumMap<>(LottoRule.class);
        result.put(LottoRule.FIRST, 0);
        result.put(LottoRule.SECOND, 1);
        result.put(LottoRule.THIRD, 2);
        result.put(LottoRule.FOURTH, 3);
        result.put(LottoRule.FIFTH, 4);

        outputView.printWinningResult(result);

        outputView.printRateOfReturn(62.5);
    }
}
