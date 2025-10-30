package lotto.util.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import lotto.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchasePriceValidatorTest {

    private final PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();

    @Test
    @DisplayName("정상값의 경우, 검증 통과하는지 확인")
    public void testValidate() {
        // given
        int input = 50_000;

        // when & then
        assertDoesNotThrow(() -> purchasePriceValidator.validate(input));
    }

    @ParameterizedTest
    @DisplayName("최소/최대 범위 이외의 값 입력 시, 예외 발생")
    @ValueSource(ints = {4_000, 120_000})
    public void testValidateRange(int input) {
        // when & then
        assertThatThrownBy(() -> purchasePriceValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_OUT_OF_RANGE));
    }

    @ParameterizedTest
    @DisplayName("로또 가격 단위 미준수 금액 입력 시, 예외 발생")
    @ValueSource(ints = {5_500, 12_345})
    public void testValidateUnit(int input) {
        // when & then
        assertThatThrownBy(() -> purchasePriceValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_INVALID_UNIT));
    }
}
