package lotto.domain;

import java.util.List;

public class UserPurchase {

    private final int price;
    private final List<Lotto> lottos;

    public UserPurchase(int price, List<Lotto> lottos) {
        this.price = price;
        this.lottos = lottos;
    }

    public int getPrice() {
        return price;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
