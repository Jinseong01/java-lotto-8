package lotto.util.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchasePriceValidatorTest {

    private final PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();

    @ParameterizedTest
    @DisplayName("최소/최대 범위 이외의 값 입력 시, 예외 발생")
    @ValueSource(ints = {4000, 120_000})
    public void testValidateRange(int input) {
        assertThatThrownBy(() -> purchasePriceValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_OUT_OF_RANGE));
    }

    @ParameterizedTest
    @DisplayName("로또 가격 단위 미준수 금액 입력 시, 예외 발생")
    @ValueSource(ints = {5_500, 12_345})
    public void testValidateUnit(int input) {
        assertThatThrownBy(() -> purchasePriceValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.getMessage(ErrorMessage.ERROR_PURCHASE_PRICE_INVALID_UNIT));
    }
}
