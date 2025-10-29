package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.UserPurchase;
import lotto.domain.WinningLotto;
import lotto.enums.LottoConfig;
import lotto.enums.LottoRule;
import lotto.service.LottoAnalyzer;
import lotto.service.LottoIssuer;
import lotto.util.BonusNumberParser;
import lotto.util.PurchasePriceParser;
import lotto.util.PurchasePriceValidator;
import lotto.util.WinningNumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoIssuer lottoIssuer;
    private final LottoAnalyzer lottoAnalyzer;
    private final PurchasePriceParser purchasePriceParser;
    private final PurchasePriceValidator purchasePriceValidator;
    private final WinningNumberParser winningNumberParser;
    private final BonusNumberParser bonusNumberParser;


    public LottoController(InputView inputView, OutputView outputView, LottoIssuer lottoIssuer,
                           LottoAnalyzer lottoAnalyzer, PurchasePriceParser purchasePriceParser,
                           PurchasePriceValidator purchasePriceValidator, WinningNumberParser winningNumberParser,
                           BonusNumberParser bonusNumberParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoIssuer = lottoIssuer;
        this.lottoAnalyzer = lottoAnalyzer;
        this.purchasePriceParser = purchasePriceParser;
        this.purchasePriceValidator = purchasePriceValidator;
        this.winningNumberParser = winningNumberParser;
        this.bonusNumberParser = bonusNumberParser;
    }

    public void run() {
        int purchasePrice = readPurchasePrice();
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

    private int readPurchasePrice() {
        while (true) {
            try {
                String input = inputView.readPurchasePrice();
                int purchasePrice = purchasePriceParser.parse(input);
                purchasePriceValidator.validate(purchasePrice);
                return purchasePrice;
            } catch (IllegalArgumentException e) {
                outputView.printBlankLine();
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
