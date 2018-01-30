package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;
    private CardInHand cardInHand;
    private int[] sum10Cards;
    private List<Map<Card, Boolean>> finalChoiceCards;

    private final int N = 5;
    private final int K = 3;

    private boolean isFinish = false;

    public Player(String name, CardInHand cardInHand) {
        this.name = name;
        this.cardInHand = cardInHand;

        sum10Cards = new int[K];
        for (int i = 1; i <= K; i++) {
            sum10Cards[i - 1] = i;
        }

        finalChoiceCards = new ArrayList<>();
        int size = cardInHand.getCardList().size();

        HashMap<Card, Boolean> map;

        for (int i = 0; i < size; i++) {
            map = new HashMap<>();
            map.put(cardInHand.getCardList().get(i), false);

            finalChoiceCards.add(map);
        }
    }

    public List<Map<Card, Boolean>> execute() {
        if (checkContainType369Diamonds()) {
            actionBestChoiceWithDiamond();
        } else {
            actionBestChoiceNoneDiamond();
        }
        return finalChoiceCards;
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

    public void actionBestChoiceWithDiamond() {

    }

    public void actionBestChoiceNoneDiamond() {
        List<Card> listCard = cardInHand.getCardList();
        int max = -1;

        do {
            int point = 0;
            Card card;
            Map<Card, Boolean> map;
            List<Card> tempList = new ArrayList<>();
            List<Card> cloneCard = new ArrayList<>(listCard);

            for (int i = 0; i < K; i++) {
                int position = sum10Cards[i];
                card = listCard.get(position - 1);
                point += card.getValue();
                tempList.add(card);

                // remove card
                cloneCard.remove(card);
            }

            int tempOfMax = 0;
            for (Card c : cloneCard) {
                tempOfMax += c.getValue();
            }

            if (mod10(point) && remainerOf10(tempOfMax) > max) {

                finalChoiceCards.removeAll(finalChoiceCards);
                for (Card c : tempList) {
                    map = new HashMap<>();
                    map.put(c, true);
                    finalChoiceCards.add(map);
                }
                for (Card c : cloneCard) {
                    map = new HashMap<>();
                    map.put(c, false);
                    finalChoiceCards.add(map);
                }

                max = remainerOf10(tempOfMax);
                tempList.removeAll(tempList);
            }

            try {
                // generate
                nextCombination();
                for (int i = 0; i < sum10Cards.length; i++) {
                    System.out.print(" " + sum10Cards[i]);
                }
                System.out.println("\n");
            } catch (Exception e) {
                // Finish Generate
            }

        } while (!isFinish);
    }

    public void nextCombination() {
        int i, j;
        i = K;
        while (i > 0 && sum10Cards[i - 1] == N - K + i)
            i--;
        if (i > 0) {
            sum10Cards[i - 1] = (sum10Cards[i - 1] + 1);
            for (j = (i - 1 + 1); j < K; j++) {
                sum10Cards[j] = (sum10Cards[i - 1] + j - (i - 1));
            }
        } else {
            isFinish = true;
        }
    }

    private static boolean mod10(int point) {
        if (point % 10 == 0) {
            return true;
        }
        return false;
    }

    private static int remainerOf10(int value) {
        return (value != 10) ? (value % 10) : value;
    }
}