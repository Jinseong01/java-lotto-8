package lotto.util.validator;

import lotto.enums.ErrorMessage;
import lotto.enums.LottoConfig;

public class PurchasePriceValidator extends LottoValidator<Integer> {

    private static final int MIN_PURCHASE_PRICE = 5_000;
    private static final int MAX_PURCHASE_PRICE = 100_000;

    @Override
    public void validate(Integer input) {
        validateRange(input);
        validateUnit(input);
    }

    private void validateRange(Integer input) {
        if (input < MIN_PURCHASE_PRICE || input > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_RANGE));
        }
    }

    private void validateUnit(Integer input) {
        if (input % LottoConfig.LOTTO_PRICE.getValue() != 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_UNIT));
        }
    }
}