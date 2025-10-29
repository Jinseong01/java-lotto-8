package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.enums.LottoConfig;
import lotto.enums.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoAnalyzerTest {

    private final LottoAnalyzer lottoAnalyzer = new LottoAnalyzer();
    private final WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    private final List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
            new Lotto(List.of(1, 2, 3, 4, 5, 8)),   // 3등
            new Lotto(List.of(1, 2, 3, 4, 8, 9)),   // 4등
            new Lotto(List.of(1, 2, 3, 8, 9, 10)),  // 5등
            new Lotto(List.of(1, 2, 8, 9, 10, 11))  // 꽝
    );


    @Test
    @DisplayName("로또 당첨 결과 확인")
    public void testMatch() {
        // when
        Map<LottoRank, Integer> result = lottoAnalyzer.match(lottos, winningLotto);

        // then
        assertThat(result.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(result.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(result.get(LottoRank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 등수가 결과에 포함되는지 확인")
    public void testAllRankInclude() {
        // when
        Map<LottoRank, Integer> result = lottoAnalyzer.match(lottos, winningLotto);

        // then
        assertThat(result).containsKeys(
                LottoRank.FIRST, LottoRank.SECOND, LottoRank.THIRD, LottoRank.FOURTH, LottoRank.FIFTH
        );
    }

    @Test
    @DisplayName("당첨된 로또 개수의 합 확인")
    public void testTotalWinningCount() {
        // when
        Map<LottoRank, Integer> result = lottoAnalyzer.match(lottos, winningLotto);
        int totalWinningCount = result.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        // then
        assertThat(totalWinningCount).isEqualTo(5);
    }

    @Test
    @DisplayName("수익률 계산 결과 확인")
    public void testCalculate() {
        // given
        int lottoCount = 7;
        int purchasePrice = lottoCount * LottoConfig.LOTTO_PRICE.getValue();
        Map<LottoRank, Integer> result = Map.of(
                LottoRank.FIRST, 0,
                LottoRank.SECOND, 0,
                LottoRank.THIRD, 0,
                LottoRank.FOURTH, 0,
                LottoRank.FIFTH, 1
        );

        // when
        double rate = lottoAnalyzer.calculateRateOfReturn(purchasePrice, result);

        // then
        assertThat(rate).isEqualTo(71.4);
    }
}
