package lotto.enums;

public enum LottoConfig {
    LOTTO_PRICE(1_000),
    LOTTO_MIN_NUMBER(1),
    LOTTO_MAX_NUMBER(45),
    LOTTO_NUMBERS_COUNT(6),
    PURCHASE_MIN_PRICE(5_000),
    PURCHASE_MAX_PRICE(100_000),
    ;

    private final int value;

    LottoConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
