package lotto.util;

public abstract class LottoParser<T> {

    public abstract T parse(String input);

    public abstract String getNonIntegerErrorMessage();

    public abstract String getBlankErrorMessage();

    public void checkBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(getBlankErrorMessage());
        }
    }
}
