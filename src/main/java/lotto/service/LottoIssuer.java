package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.enums.LottoConfig;

public class LottoIssuer {
    public List<Lotto> issue(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(LottoConfig.LOTTO_MIN_NUMBER.getValue(),
                            LottoConfig.LOTTO_MAX_NUMBER.getValue(),
                            LottoConfig.LOTTO_NUMBERS_COUNT.getValue())
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(lottoNumber));
        }
        return lottos;
    }
}
