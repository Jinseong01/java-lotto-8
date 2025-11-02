package lotto.util.parser;

public abstract class IntegerParser extends IntegerBasedParser<Integer> {
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
