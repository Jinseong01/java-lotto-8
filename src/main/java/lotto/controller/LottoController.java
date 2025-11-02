package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.UserPurchase;
import lotto.domain.WinningLotto;
import lotto.enums.LottoConfig;
import lotto.enums.LottoRank;
import lotto.service.LottoAnalyzer;
import lotto.service.LottoIssuer;
import lotto.util.InputProcessor;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoIssuer lottoIssuer;
    private final LottoAnalyzer lottoAnalyzer;
    private final InputProcessor inputProcessor;

    public LottoController(InputView inputView, OutputView outputView, LottoIssuer lottoIssuer,
                           LottoAnalyzer lottoAnalyzer, InputProcessor inputProcessor) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoIssuer = lottoIssuer;
        this.lottoAnalyzer = lottoAnalyzer;
        this.inputProcessor = inputProcessor;
    }

    public void run() {
        int purchasePrice = readPurchasePrice();
        List<Lotto> lottos = issueLottos(purchasePrice);

        UserPurchase userPurchase = new UserPurchase(purchasePrice, lottos);
        WinningLotto winningLotto = readWinningLotto();

        analyzeLottos(userPurchase, winningLotto);
    }

    private int readPurchasePrice() {
        while (true) {
            try {
                String input = inputView.readPurchasePrice();
                int purchasePrice = inputProcessor.processPurchasePrice(input);
                outputView.printBlankLine();
                return purchasePrice;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Lotto> issueLottos(int purchasePrice) {
        int lottoCount = purchasePrice / LottoConfig.LOTTO_PRICE.getValue();
        outputView.printLottoCount(lottoCount);
        List<Lotto> lottos = lottoIssuer.issue(lottoCount);
        outputView.printLottoNumber(lottos);
        return lottos;
    }

    private WinningLotto readWinningLotto() {
        List<Integer> winningNumbers = readWinningNumber();
        int bonusNumber = readBonusNumber(winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private List<Integer> readWinningNumber() {
        while (true) {
            try {
                String input = inputView.readWinningNumber();
                List<Integer> parsedWinningNumbers = inputProcessor.processWinningNumbers(input);
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
                String input = inputView.readBonusNumber();
                int bonusNumber = inputProcessor.processBonusNumber(input, parsedWinningNumbers);
                outputView.printBlankLine();
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void analyzeLottos(UserPurchase userPurchase, WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = lottoAnalyzer.match(userPurchase.getLottos(), winningLotto);
        outputView.printWinningResult(result);
        double rateOfReturn = lottoAnalyzer.calculateRateOfReturn(userPurchase.getPrice(), result);
        outputView.printRateOfReturn(rateOfReturn);
    }
}
