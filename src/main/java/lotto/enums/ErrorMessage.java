package lotto.enums;

public enum ErrorMessage {
    ERROR_PREFIX("[ERROR] "),
    ERROR_WINNING_NUMBER_BLANK("당첨 번호를 입력하지 않았습니다."),
    ERROR_WINNING_NUMBER_TRIM("당첨 번호 앞 또는 뒤의 공백은 허용하지 않습니다."),
    ERROR_WINNING_NUMBER_NON_INTEGER("당첨 번호는 정수 이외의 값을 허용하지 않습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public static String getMessage(ErrorMessage errorMessage) {
        return ERROR_PREFIX.message + errorMessage.message;
    }
}
