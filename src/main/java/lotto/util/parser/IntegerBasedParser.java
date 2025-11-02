package lotto.util.parser;

public abstract class IntegerBasedParser<T> extends LottoParser<T> {

    protected abstract String getNonIntegerErrorMessage();
}
