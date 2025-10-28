package lotto.enums;

public enum OutputMessage {
    VIEW_LOTTO_COUNT("%d개를 구매했습니다."),
    VIEW_WINNING_HEADER("당첨 통계\n---"),
    VIEW_WINNING_RESULT("%s (%s원) - %d개"),
    VIEW_RATE_OF_RETURN("총 수익률은 %.1f%%입니다."),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
