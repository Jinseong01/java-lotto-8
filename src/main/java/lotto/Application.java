package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoAnalyzer;
import lotto.service.LottoIssuer;
import lotto.util.parser.BonusNumberParser;
import lotto.util.parser.PurchasePriceParser;
import lotto.util.validator.BonusNumberValidator;
import lotto.util.validator.PurchasePriceValidator;
import lotto.util.parser.WinningNumberParser;
import lotto.util.validator.WinningNumberValidator;
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
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        BonusNumberParser bonusNumberParser = new BonusNumberParser();
        BonusNumberValidator bonusNumberValidator = new BonusNumberValidator();
        LottoAnalyzer lottoAnalyzer = new LottoAnalyzer();

        LottoController lottoController = new LottoController(inputView, outputView, lottoIssuer, lottoAnalyzer,
                purchasePriceParser, purchasePriceValidator, winningNumberParser, winningNumberValidator,
                bonusNumberParser, bonusNumberValidator);

        lottoController.run();
    }
}
