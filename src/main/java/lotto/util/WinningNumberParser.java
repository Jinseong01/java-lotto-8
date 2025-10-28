package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.enums.ErrorMessage;

public class WinningNumberParser {

    private static final String DELIMITER = ",";

    public List<Integer> parse(String input) {
        checkBlank(input);
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .peek(this::checkTrim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_ONLY_INTEGER));
        }
    }

    private void checkBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_BLANK));
        }
    }

    private void checkTrim(String input) {
        if (!input.equals(input.trim())) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_TRIM));
        }
    }
}
