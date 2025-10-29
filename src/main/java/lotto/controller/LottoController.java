package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.UserPurchase;
import lotto.domain.WinningLotto;
import lotto.enums.LottoConfig;
import lotto.enums.LottoRank;
import lotto.service.LottoAnalyzer;
import lotto.service.LottoIssuer;
import lotto.util.parser.BonusNumberParser;
import lotto.util.parser.PurchasePriceParser;
import lotto.util.parser.WinningNumberParser;
import lotto.util.validator.BonusNumberValidator;
import lotto.util.validator.PurchasePriceValidator;
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
    private final BonusNumberValidator bonusNumberValidator;


    public LottoController(InputView inputView, OutputView outputView, LottoIssuer lottoIssuer,
                           LottoAnalyzer lottoAnalyzer, PurchasePriceParser purchasePriceParser,
                           PurchasePriceValidator purchasePriceValidator, WinningNumberParser winningNumberParser,
                           BonusNumberParser bonusNumberParser, BonusNumberValidator bonusNumberValidator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoIssuer = lottoIssuer;
        this.lottoAnalyzer = lottoAnalyzer;
        this.purchasePriceParser = purchasePriceParser;
        this.purchasePriceValidator = purchasePriceValidator;
        this.winningNumberParser = winningNumberParser;
        this.bonusNumberParser = bonusNumberParser;
        this.bonusNumberValidator = bonusNumberValidator;
    }

    public void run() {
        int purchasePrice = readPurchasePrice();

        int lottoPrice = LottoConfig.LOTTO_PRICE.getValue();
        int lottoCount = purchasePrice / lottoPrice;
        outputView.printLottoCount(lottoCount);

        List<Lotto> lottos = lottoIssuer.issue(lottoCount);
        outputView.printLottoNumber(lottos);

        UserPurchase userPurchase = new UserPurchase(purchasePrice, lottos);

        List<Integer> parsedWinningNumbers = readWinningNumber();

        int bonusNumber = readBonusNumber(parsedWinningNumbers);

        WinningLotto winningLotto = new WinningLotto(parsedWinningNumbers, bonusNumber);

        Map<LottoRank, Integer> result = lottoAnalyzer.match(userPurchase.getLottos(), winningLotto);

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

                outputView.printBlankLine();
                return purchasePrice;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> readWinningNumber() {
        while (true) {
            try {
                String input = inputView.readWinningNumber();
                List<Integer> parsedWinningNumbers = winningNumberParser.parse(input);
                // TODO WinningNumberValidator 구현
                outputView.printBlankLine();
                return parsedWinningNumbers;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int readBonusNumber(List<Integer> parsedWinningNumbers) {
        while (true) {
            try {
                String input = inputView.readBounusNumber();
                int bonusNumber = bonusNumberParser.parse(input);

                List<Integer> winningLottoNumbers = new ArrayList<>(parsedWinningNumbers);
                winningLottoNumbers.add(bonusNumber);
                bonusNumberValidator.validate(winningLottoNumbers);
                outputView.printBlankLine();

                return bonusNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
