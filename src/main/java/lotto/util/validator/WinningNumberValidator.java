package lotto.util.validator;

import java.util.List;
import lotto.enums.ErrorMessage;
import lotto.enums.LottoConfig;

public class WinningNumberValidator extends LottoValidator<List<Integer>> {

    private static final int MIN_WINNING_NUMBER = LottoConfig.LOTTO_MIN_NUMBER.getValue();
    private static final int MAX_WINNING_NUMBER = LottoConfig.LOTTO_MAX_NUMBER.getValue();
    private static final int WINNING_NUMBER_COUNT = LottoConfig.LOTTO_NUMBERS_COUNT.getValue();

    @Override
    public void validate(List<Integer> input) {
        validateRange(input);
        validateDuplicate(input);
        validateCount(input);
    }

    private void validateRange(List<Integer> input) {
        if (input.stream().anyMatch(value -> value < MIN_WINNING_NUMBER || value > MAX_WINNING_NUMBER)) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_OUT_OF_RANGE),
                            MIN_WINNING_NUMBER, MAX_WINNING_NUMBER));
        }
    }

    private void validateDuplicate(List<Integer> input) {
        if (input.stream().distinct().count() != input.size()) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_DUPLICATE));
        }
    }

    private void validateCount(List<Integer> input) {
        if (input.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_INVALID_COUNT),
                            WINNING_NUMBER_COUNT));
        }
    }
}