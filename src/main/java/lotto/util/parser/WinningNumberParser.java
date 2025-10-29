package lotto.util.parser;

import java.util.Arrays;
import java.util.List;
import lotto.enums.ErrorMessage;

public class WinningNumberParser extends LottoParser<List<Integer>> {

    private static final String DELIMITER = ",";

    @Override
    public List<Integer> parse(String input) {
        checkBlank(input);
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .peek(this::checkTrim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(getNonIntegerErrorMessage());
        }
    }

    @Override
    public String getNonIntegerErrorMessage() {
        return ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_NON_INTEGER);
    }

    @Override
    public String getBlankErrorMessage() {
        return ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_BLANK);
    }

    private void checkTrim(String input) {
        if (!input.equals(input.trim())) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_TRIM));
        }
    }
}
