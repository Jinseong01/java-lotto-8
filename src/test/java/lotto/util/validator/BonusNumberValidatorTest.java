package lotto.util.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberValidatorTest {

    private final BonusNumberValidator bonusNumberValidator = new BonusNumberValidator();

    private static List<List<Integer>> outOfRange() {
        return Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6, 0),
                Arrays.asList(1, 2, 3, 4, 5, 6, 46)
        );
    }

    private static List<List<Integer>> duplicate() {
        return Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6, 1),
                Arrays.asList(1, 2, 3, 4, 5, 6, 6)
        );
    }

    @ParameterizedTest
    @DisplayName("최소/최대 범위 이외의 값 입력 시, 예외 발생")
    @MethodSource("outOfRange")
    public void testValidateRange(List<Integer> input) {
        // when & then
        assertThatThrownBy(() -> bonusNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_BONUS_NUMBER_OUT_OF_RANGE));
    }

    @ParameterizedTest
    @DisplayName("당첨 번호와 중복 시, 예외 발생")
    @MethodSource("duplicate")
    public void testValidateDuplicate(List<Integer> input) {
        // when & then
        assertThatThrownBy(() -> bonusNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_BONUS_NUMBER_DUPLICATE));
    }
}
