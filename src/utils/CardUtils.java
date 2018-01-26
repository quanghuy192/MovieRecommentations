package utils;

import domain.Card;

/**
 * Created by huy on 1/26/18.
 */
public class CardUtils {
    private CardUtils() {
    }

    public static int convertToOrderInDeck(Card card) {
        int num = card.getValue();
        int typeInNum = card.getType().ordinal();
        return num * (typeInNum + 1);
    }
}
