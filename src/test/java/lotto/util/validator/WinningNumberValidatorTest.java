package lotto.util.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningNumberValidatorTest {

    private final WinningNumberValidator winningNumberValidator = new WinningNumberValidator();

    private static List<List<Integer>> outOfRange() {
        return Arrays.asList(
                Arrays.asList(0, 1, 2, 3, 4, 5),
                Arrays.asList(1, 2, 3, 4, 5, 46)
        );
    }

    private static List<List<Integer>> duplicate() {
        return Arrays.asList(
                Arrays.asList(1, 1, 2, 3, 4, 5),
                Arrays.asList(1, 2, 3, 4, 5, 5)
        );
    }

    public static List<List<Integer>> invalidCount() {
        return Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 2, 3, 4, 5, 6, 7)
        );
    }

    @ParameterizedTest
    @DisplayName("최소/최대 범위 이외의 값 입력 시, 예외 발생")
    @MethodSource("outOfRange")
    public void testValidateRange(List<Integer> input) {
        // when & then
        assertThatThrownBy(() -> winningNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_OUT_OF_RANGE));
    }

    @ParameterizedTest
    @DisplayName("당첨 번호끼리 중복 시, 예외 발생")
    @MethodSource("duplicate")
    public void testValidateDuplicate(List<Integer> input) {
        // when & then
        assertThatThrownBy(() -> winningNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_DUPLICATE));
    }

    @ParameterizedTest
    @DisplayName("잘못된 개수의 당첨 번호 입력 시, 예외 발생")
    @MethodSource("invalidCount")
    public void testValidateInvalidCount(List<Integer> input) {
        assertThatThrownBy(() -> winningNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_INVALID_COUNT));
    }
}
