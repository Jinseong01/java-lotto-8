package lotto.util;

import lotto.enums.ErrorMessage;

public class PurchasePriceParser extends IntegerParser {
    @Override
    public String getNonIntegerErrorMessage() {
        return ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_NON_INTEGER);
    }

    @Override
    public String getBlankErrorMessage() {
        return ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_BLANK);
    }
}
