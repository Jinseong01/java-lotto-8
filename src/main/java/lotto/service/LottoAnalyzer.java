package lotto.service;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.enums.LottoRank;

public class LottoAnalyzer {
    public Map<LottoRank, Integer> match(List<Lotto> lottos, WinningLotto winningLotto) {
        EnumMap<LottoRank, Integer> result = initResult();

        List<Integer> winningNumbers = winningLotto.getLotto().getNumbers();
        int bonusNumber = winningLotto.getBonus();

        lottos.forEach(lotto -> checkPrize(lotto, winningNumbers, bonusNumber, result));

        return result;
    }

    public double calculateRateOfReturn(int price, Map<LottoRank, Integer> lottoResult) {
        int totalWinningPrice = 0;

        for (Map.Entry<LottoRank, Integer> entry : lottoResult.entrySet()) {
            LottoRank lottoRank = entry.getKey();
            int count = entry.getValue();

            totalWinningPrice += lottoRank.getPrize() * count;
        }

        double rateOfReturn = ((double) totalWinningPrice / price) * 100;
        return Math.round(rateOfReturn * 10) / 10.0;
    }

    private EnumMap<LottoRank, Integer> initResult() {
        EnumMap<LottoRank, Integer> map = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(rank -> map.put(rank, 0));
        return map;
    }

    private void checkPrize(Lotto lotto, List<Integer> winningNumbers, int bonusNumber,
                            EnumMap<LottoRank, Integer> result) {
        int matchCount = countMatchingNumbers(lotto.getNumbers(), winningNumbers);
        boolean bonusMatch = false;

        if (matchCount == 5) {
            bonusMatch = isBonusMatched(lotto.getNumbers(), bonusNumber);
        }

        LottoRank lottoRank = determineLottoRank(matchCount, bonusMatch);

        if (lottoRank != null) {
            result.put(lottoRank, result.get(lottoRank) + 1);
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

    private LottoRank determineLottoRank(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return LottoRank.FIRST;
        }
        if (matchCount == 5 && bonusMatch) {
            return LottoRank.SECOND;
        }
        if (matchCount == 5) {
            return LottoRank.THIRD;
        }
        if (matchCount == 4) {
            return LottoRank.FOURTH;
        }
        if (matchCount == 3) {
            return LottoRank.FIFTH;
        }
        return null;
    }
}
