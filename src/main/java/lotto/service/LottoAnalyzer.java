package lotto.service;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.enums.LottoRule;

public class LottoAnalyzer {
    public Map<LottoRule, Integer> match(List<Lotto> lottos, WinningLotto winningLotto) {
        EnumMap<LottoRule, Integer> result = initResult();

        List<Integer> winningNumbers = winningLotto.getLotto().getNumbers();
        int bonusNumber = winningLotto.getBonus();

        lottos.forEach(lotto -> checkPrize(lotto, winningNumbers, bonusNumber, result));

        return result;
    }

    public double calculateRateOfReturn(int price, Map<LottoRule, Integer> lottoResult) {
        int totalWinningPrice = 0;

        for (Map.Entry<LottoRule, Integer> entry : lottoResult.entrySet()) {
            LottoRule rule = entry.getKey();
            int count = entry.getValue();

            totalWinningPrice += rule.getPrize() * count;
        }

        double rateOfReturn = ((double) totalWinningPrice / price) * 100;
        return Math.round(rateOfReturn * 10) / 10.0;
    }

    private EnumMap<LottoRule, Integer> initResult() {
        EnumMap<LottoRule, Integer> map = new EnumMap<>(LottoRule.class);
        Arrays.stream(LottoRule.values())
                .forEach(rule -> map.put(rule, 0));
        return map;
    }

    private void checkPrize(Lotto lotto, List<Integer> winningNumbers, int bonusNumber,
                            EnumMap<LottoRule, Integer> result) {
        int matchCount = countMatchingNumbers(lotto.getNumbers(), winningNumbers);
        boolean bonusMatch = false;

        if (matchCount == 5) {
            bonusMatch = isBonusMatched(lotto.getNumbers(), bonusNumber);
        }

        LottoRule lottoRule = determineLottoRule(matchCount, bonusMatch);

        if (lottoRule != null) {
            result.put(lottoRule, result.get(lottoRule) + 1);
        }
    }

    private int countMatchingNumbers(List<Integer> numbers, List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count()
                ;
    }

    private boolean isBonusMatched(List<Integer> numbers, int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private LottoRule determineLottoRule(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return LottoRule.FIRST;
        }
        if (matchCount == 5 && bonusMatch) {
            return LottoRule.SECOND;
        }
        if (matchCount == 5) {
            return LottoRule.THIRD;
        }
        if (matchCount == 4) {
            return LottoRule.FOURTH;
        }
        if (matchCount == 3) {
            return LottoRule.FIFTH;
        }
        return null;
    }
}
