package lotto.util;

import java.util.ArrayList;
import java.util.List;
import lotto.util.parser.BonusNumberParser;
import lotto.util.parser.PurchasePriceParser;
import lotto.util.parser.WinningNumberParser;
import lotto.util.validator.BonusNumberValidator;
import lotto.util.validator.PurchasePriceValidator;
import lotto.util.validator.WinningNumberValidator;

public class InputProcessor {
    private final PurchasePriceParser purchasePriceParser;
    private final PurchasePriceValidator purchasePriceValidator;
    private final WinningNumberParser winningNumberParser;
    private final WinningNumberValidator winningNumberValidator;
    private final BonusNumberParser bonusNumberParser;
    private final BonusNumberValidator bonusNumberValidator;

    public InputProcessor(PurchasePriceParser purchasePriceParser, PurchasePriceValidator purchasePriceValidator,
                          WinningNumberParser winningNumberParser, WinningNumberValidator winningNumberValidator,
                          BonusNumberParser bonusNumberParser, BonusNumberValidator bonusNumberValidator) {
        this.purchasePriceParser = purchasePriceParser;
        this.purchasePriceValidator = purchasePriceValidator;
        this.winningNumberParser = winningNumberParser;
        this.winningNumberValidator = winningNumberValidator;
        this.bonusNumberParser = bonusNumberParser;
        this.bonusNumberValidator = bonusNumberValidator;
    }

    public int processPurchasePrice(String input) {
        int purchasePrice = purchasePriceParser.parse(input);
        purchasePriceValidator.validate(purchasePrice);
        return purchasePrice;
    }

    public List<Integer> processWinningNumbers(String input) {
        List<Integer> winningNumbers = winningNumberParser.parse(input);
        winningNumberValidator.validate(winningNumbers);
        return winningNumbers;
    }

    public int processBonusNumber(String input, List<Integer> winningNumbers) {
        int bonusNumber = bonusNumberParser.parse(input);

        List<Integer> winningLottoNumbers = new ArrayList<>(winningNumbers);
        winningLottoNumbers.add(bonusNumber);
        bonusNumberValidator.validate(winningLottoNumbers);

        return bonusNumber;
    }
}
