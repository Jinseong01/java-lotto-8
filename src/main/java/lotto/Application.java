package lotto;

import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();
        System.out.println(inputView.readPurchasePrice());
        System.out.println(inputView.readWinningNumber());
        System.out.println(inputView.readBounusNumber());
    }
}
