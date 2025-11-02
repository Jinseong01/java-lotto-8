package lotto.domain;

import java.util.List;
import lotto.enums.ErrorMessage;
import lotto.enums.LottoConfig;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != LottoConfig.LOTTO_NUMBERS_COUNT.getValue()) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.getMessage(ErrorMessage.ERROR_LOTTO_INVALID_COUNT),
                            LottoConfig.LOTTO_NUMBERS_COUNT.getValue()));
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_LOTTO_NUMBER_DUPLICATE));
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < LottoConfig.LOTTO_MIN_NUMBER.getValue()
                || number > LottoConfig.LOTTO_MAX_NUMBER.getValue())) {
            throw new IllegalArgumentException(
                    ErrorMessage.getMessage(ErrorMessage.ERROR_LOTTO_NUMBER_OUT_OF_RANGE));
        }
    }
}
