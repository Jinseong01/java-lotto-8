package lotto.util.validator;

import java.util.List;
import lotto.enums.ErrorMessage;
import lotto.enums.LottoConfig;

public class BonusNumberValidator extends LottoValidator<List<Integer>> {

    private static final int MIN_BONUS_NUMBER = LottoConfig.LOTTO_MIN_NUMBER.getValue();
    private static final int MAX_BONUS_NUMBER = LottoConfig.LOTTO_MAX_NUMBER.getValue();

    @Override
    public void validate(List<Integer> input) {
        List<Integer> winningNumbers = input.subList(0, input.size() - 1);
        int bonusNumber = input.getLast();

        validateRange(bonusNumber);
        validateDuplicate(bonusNumber, winningNumbers);
    }

    private void validateRange(Integer input) {
        if (input < MIN_BONUS_NUMBER || input > MAX_BONUS_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_BONUS_NUMBER_OUT_OF_RANGE));
        }
    }

    private void validateDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(ErrorMessage.ERROR_BONUS_NUMBER_DUPLICATE));
        }
    }
}