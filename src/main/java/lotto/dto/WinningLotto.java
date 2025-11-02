package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonus;

    public WinningLotto(List<Integer> numbers, int bonus) {
        this.lotto = new Lotto(numbers);
        this.bonus = bonus;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonus() {
        return bonus;
    }
}
