package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumberParserTest {

    private final WinningNumberParser winningNumberParser = new WinningNumberParser();

    @Test
    @DisplayName("정상 입력 문자열의 경우, 파싱되는지 확인")
    public void testParse() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> result = winningNumberParser.parse(input);

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @DisplayName("빈/공백 문자열 입력 시, 예외 발생")
    @ValueSource(strings = {"", " "})
    public void testParseBlank(String input) {
        // when & then
        assertThatThrownBy(() -> winningNumberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_BLANK));
    }

    @Test
    @DisplayName("번호 앞 또는 뒤에 공백을 포함하여 입력 시, 예외 발생")
    public void testParseTrim() {
        // given
        String input = "1, 2, 3, 4,5,6";

        // when & then
        assertThatThrownBy(() -> winningNumberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_TRIM));
    }

    @ParameterizedTest
    @DisplayName("정수가 아닌 값 입력 시, 예외 발생")
    @ValueSource(strings = {"1,A,3,4,5,6", "1,0.1,3,4,5,6", "1,,3, ,5,6"})
    public void testParseNonInteger(String input) {
        // when & then
        assertThatThrownBy(() -> winningNumberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_WINNING_NUMBER_NON_INTEGER));
    }
}
