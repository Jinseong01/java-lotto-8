package lotto.util;

public abstract class IntegerParser extends LottoParser<Integer> {
    @Override
    public Integer parse(String input) {
        checkBlank(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(getNonIntegerErrorMessage());
        }
    }
}
