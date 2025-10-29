package lotto;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.UserPurchase;
import lotto.domain.WinningLotto;
import lotto.enums.LottoConfig;
import lotto.enums.LottoRule;
import lotto.service.LottoIssuer;
import lotto.service.LottoAnalyzer;
import lotto.util.BonusNumberParser;
import lotto.util.PurchasePriceParser;
import lotto.util.WinningNumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoIssuer lottoIssuer = new LottoIssuer();
        PurchasePriceParser purchasePriceParser = new PurchasePriceParser();
        WinningNumberParser winningNumberParser = new WinningNumberParser();
        BonusNumberParser bonusNumberParser = new BonusNumberParser();
        LottoAnalyzer lottoAnalyzer = new LottoAnalyzer();

        int purchasePrice = purchasePriceParser.parse(inputView.readPurchasePrice());
        outputView.printBlankLine();

        int lottoPrice = LottoConfig.LOTTO_PRICE.getValue();
        int lottoCount = purchasePrice / lottoPrice;
        outputView.printLottoCount(lottoCount);

        List<Lotto> lottos = lottoIssuer.issue(lottoCount);
        outputView.printLottoNumber(lottos);
        outputView.printBlankLine();

        UserPurchase userPurchase = new UserPurchase(purchasePrice, lottos);

        String winningNumbers = inputView.readWinningNumber();
        outputView.printBlankLine();

        List<Integer> parsedWinningNumbers = winningNumberParser.parse(winningNumbers);

        int bonusNumber = bonusNumberParser.parse(inputView.readBounusNumber());
        outputView.printBlankLine();

        WinningLotto winningLotto = new WinningLotto(parsedWinningNumbers, bonusNumber);

        Map<LottoRule, Integer> result = lottoAnalyzer.match(userPurchase.getLottos(), winningLotto);

        outputView.printWinningResult(result);

        double rateOfReturn = lottoAnalyzer.calculateRateOfReturn(userPurchase.getPrice(), result);

        outputView.printRateOfReturn(rateOfReturn);
    }
}
