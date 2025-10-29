package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoAnalyzer;
import lotto.service.LottoIssuer;
import lotto.util.BonusNumberParser;
import lotto.util.PurchasePriceParser;
import lotto.util.PurchasePriceValidator;
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
        PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();
        WinningNumberParser winningNumberParser = new WinningNumberParser();
        BonusNumberParser bonusNumberParser = new BonusNumberParser();
        LottoAnalyzer lottoAnalyzer = new LottoAnalyzer();

        LottoController lottoController = new LottoController(inputView, outputView, lottoIssuer, lottoAnalyzer,
                purchasePriceParser, purchasePriceValidator, winningNumberParser, bonusNumberParser);

        lottoController.run();
    }
}
