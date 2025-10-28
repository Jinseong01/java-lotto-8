package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.enums.LottoConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoIssuerTest {

    private final int lottoCount = 5;
    private final LottoIssuer lottoIssuer = new LottoIssuer();
    private final List<Lotto> lottos = lottoIssuer.issue(lottoCount);

    @Test
    @DisplayName("발행된 로또 수량 확인")
    public void testLottoCount() {
        // when
        int size = lottos.size();

        // then
        assertThat(size).isEqualTo(lottoCount);
    }

    @Test
    @DisplayName("각 로또의 번호 개수가 설정(6)과 동일한지 확인")
    public void testLottoNumbersCount() {
        // when & then
        lottos.forEach(lotto -> {
            assertThat(lotto.getNumbers().size()).isEqualTo(LottoConfig.LOTTO_NUMBERS_COUNT.getValue());
        });
    }

    @Test
    @DisplayName("각 로또의 번호 범위가 설정(1~45)과 동일한지 확인")
    public void testLottoNumbersRange() {
        // when & then
        lottos.forEach(lotto -> {
            lotto.getNumbers().forEach(number -> {
                assertThat(number)
                        .isGreaterThanOrEqualTo(LottoConfig.LOTTO_MIN_NUMBER.getValue())
                        .isLessThanOrEqualTo(LottoConfig.LOTTO_MAX_NUMBER.getValue());
            });
        });
    }

    @Test
    @DisplayName("각 로또 번호가 중복되지 않았는지 확인")
    public void testLottoNumbersUnique() {
        // when & then
        lottos.forEach(lotto -> {
            List<Integer> numbers = lotto.getNumbers();
            long distinctCount = numbers.stream().distinct().count();
            assertThat(distinctCount).isEqualTo(LottoConfig.LOTTO_NUMBERS_COUNT.getValue());
        });
    }
}
