package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;
    private CardInHand cardInHand;
    private List<Card> listCard3, listCard;
    private static short[] existCard;
    private static List<HashMap<Card, Boolean>> currentListCard;

    private static final short N = 5;
    private static final short K = 3;

    private static boolean isFinish = false;

    public Player(String name, CardInHand cardInHand) {
        this.name = name;
        this.cardInHand = cardInHand;
    }

    public void prepare() {
        if (checkContainType369Diamonds()) {
            getBestChoiceWithDiamond();
        } else {
            getBestChoiceNoneDiamond();
        }
    }

    public boolean checkContainType369Diamonds() {
        Card diamond3 = new Card(3, Type.DIAMONDS);
        Card diamond6 = new Card(6, Type.DIAMONDS);
        Card diamond9 = new Card(9, Type.DIAMONDS);
        if (cardInHand.getCardList().contains(diamond3)
                || cardInHand.getCardList().contains(diamond6)
                || cardInHand.getCardList().contains(diamond9)) {
            return true;
        }
        return false;
    }

    public void getBestChoiceWithDiamond() {

    }

    public void getBestChoiceNoneDiamond() {
        List<Card> listCard = cardInHand.getCardList();
        short max = -1;

        do {
            short point = 0;
            Card card;
            Map<Card, Boolean> map;
            List<Card> tempList = new ArrayList<>();
            List<Card> cloneCard = new ArrayList<>(listCard);

            for (int i = 0; i < K; i++) {
                int position = existCard[i];
                card = listCard.get(position - 1);
                point += card.getValue();
                tempList.add(card);

                // remove card
                cloneCard.remove(card);
            }

            short tempOfMax = 0;
            for (Card c : cloneCard) {
                tempOfMax += c.getValue();
            }

            if (mod10(point) && remainerOf10(tempOfMax) > max) {

                currentListCard.removeAll(currentListCard);

                for (Card c : tempList) {
                    map = new HashMap<>();
                    map.put(c, true);
                    // currentListCard.add(map);
                }

                for (Card c : cloneCard) {
                    map = new HashMap<>();
                    map.put(c, false);
                    // currentListCard.add(map);
                }

                max = remainerOf10(tempOfMax);
                tempList.removeAll(tempList);
            }

            try {
                // generate
                nextCombination();
            } catch (Exception e) {
                // Finish Generate
            }

        } while (!isFinish);
    }

    private static void nextCombination() {

        short i, j;
        i = K;
        while (i >= 0 && existCard[i - 1] == N - K + i)
            i--;

        if (i >= 0) {
            existCard[i - 1] = (short) (existCard[i - 1] + 1);
            for (j = (short) (i - 1 + 1); j < K; j++) {
                existCard[j] = (short) (existCard[i - 1] + j - (i - 1));
            }
        } else {
            isFinish = true;
        }
    }

    private static boolean mod10(short point) {
        if (point % 10 == 0) {
            return true;
        }
        return false;
    }

    private static short remainerOf10(short value) {
        return (value != 10) ? (short) (value % 10) : value;
    }
}
