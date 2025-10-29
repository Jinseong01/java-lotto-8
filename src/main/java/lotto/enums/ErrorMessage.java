package lotto.enums;

public enum ErrorMessage {
    ERROR_PREFIX("[ERROR] "),
    ERROR_PURCHASE_PRICE_BLANK("구매 금액을 입력하지 않았습니다."),
    ERROR_PURCHASE_PRICE_NON_INTEGER("구매 금액은 정수 이외의 값을 허용하지 않습니다."),
    ERROR_PURCHASE_PRICE_RANGE("구매 금액은 최소 5,000원에서 최대 100,000원입니다."),
    ERROR_PURCHASE_PRICE_UNIT("구매 금액은 로또 1장 가격 단위로 입력해야 합니다."),
    ERROR_WINNING_NUMBER_BLANK("당첨 번호를 입력하지 않았습니다."),
    ERROR_WINNING_NUMBER_TRIM("당첨 번호 앞 또는 뒤의 공백은 허용하지 않습니다."),
    ERROR_WINNING_NUMBER_NON_INTEGER("당첨 번호는 정수 이외의 값을 허용하지 않습니다."),
    ERROR_BONUS_NUMBER_BLANK("보너스 번호를 입력하지 않았습니다."),
    ERROR_BONUS_NUMBER_NON_INTEGER("보너스 번호는 정수 이외의 값을 허용하지 않습니다."),
    ERROR_BONUS_NUMBER_RANGE("보너스 번호는 최소 1에서 최대 45입니다."),
    ERROR_BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨 번호 이외의 값을 입력해야 합니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public static String getMessage(ErrorMessage errorMessage) {
        return ERROR_PREFIX.message + errorMessage.message;
    }
}
