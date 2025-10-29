package lotto.util;

import lotto.enums.ErrorMessage;

public class BonusNumberParser extends IntegerParser {
    @Override
    public String getNonIntegerErrorMessage() {
        return ErrorMessage.getMessage(ErrorMessage.ERROR_BONUS_NUMBER_NON_INTEGER);
    }

    @Override
    public String getBlankErrorMessage() {
        return ErrorMessage.getMessage(ErrorMessage.ERROR_BONUS_NUMBER_BLANK);
    }
}
