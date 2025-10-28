package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class LottoIssuer {

    private static final int LOTTO_MIN_NUM = 1;
    private static final int LOTTO_MAX_NUM = 45;
    private static final int LOTTO_NUMBERS_COUNT = 6;

    public List<Lotto> issue(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUM, LOTTO_MAX_NUM,
                            LOTTO_NUMBERS_COUNT)
                    .stream()
                    .sorted()
                    .toList();
            lottos.add(new Lotto(lottoNumber));
        }
        return lottos;
    }
}
