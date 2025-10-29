package lotto.util.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchasePriceParserTest {

    private final PurchasePriceParser purchasePriceParser = new PurchasePriceParser();

    @Test
    @DisplayName("정상 입력 문자열의 경우, 파싱되는지 확인")
    public void testParse() {
        // given
        String input = "8000";

        // when
        int result = purchasePriceParser.parse(input);

        // then
        assertThat(result).isEqualTo(8000);
    }

    @ParameterizedTest
    @DisplayName("빈/공백 문자열 입력 시, 예외 발생")
    @ValueSource(strings = {"", " "})
    public void testParseBlank(String input) {
        // when & then
        assertThatThrownBy(() -> purchasePriceParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_BLANK));
    }

    @ParameterizedTest
    @DisplayName("정수가 아닌 값 입력 시, 예외 발생")
    @ValueSource(strings = {"A", "0.1", "."})
    public void testParseNonInteger(String input) {
        // when & then
        assertThatThrownBy(() -> purchasePriceParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_NON_INTEGER));

    }
}
