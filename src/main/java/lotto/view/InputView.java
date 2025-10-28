package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.enums.InputMessage;

public class InputView {
    public String readPurchasePrice() {
        System.out.println(InputMessage.VIEW_PURCHASE_PRICE.getMessage());
        return Console.readLine();
    }

    public String readWinningNumber() {
        System.out.println(InputMessage.VIEW_WINNING_NUMBER.getMessage());
        return Console.readLine();
    }

    public String readBounusNumber() {
        System.out.println(InputMessage.VIEW_BONUS_NUMBER.getMessage());
        return Console.readLine();
    }
}
