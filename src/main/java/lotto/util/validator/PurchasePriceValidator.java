package lotto.util.validator;

import lotto.enums.ErrorMessage;
import lotto.enums.LottoConfig;

public class PurchasePriceValidator extends LottoValidator<Integer> {

    private static final int MIN_PURCHASE_PRICE = LottoConfig.PURCHASE_MIN_PRICE.getValue();
    private static final int MAX_PURCHASE_PRICE = LottoConfig.PURCHASE_MAX_PRICE.getValue();

    @Override
    public void validate(Integer input) {
        validateRange(input);
        validateUnit(input);
    }

    private void validateRange(Integer input) {
        if (input < MIN_PURCHASE_PRICE || input > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_OUT_OF_RANGE),
                            MIN_PURCHASE_PRICE, MAX_PURCHASE_PRICE));
        }
    }

    private void validateUnit(Integer input) {
        if (input % LottoConfig.LOTTO_PRICE.getValue() != 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_INVALID_UNIT));
        }
    }
}