package domain;

import data.ExpUtils;
import stochastic_gradient_descent.StochasticGradientDescent;

import java.util.*;

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
    }

    public CardInHand getCardInHand() {
        return cardInHand;
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

    private void nextCombination() {
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

    public Card showCard() {
        List<Card> cards = getListCardResult(true);
        Random r = new Random();
        return cards.get(r.nextInt(1));
    }

    public int showPoint() {
        List<Card> cards = getListCardResult(true);
        int point = 0;
        for (Card c : cards) {
            point += c.getValue();
        }
        return remainerOf10(point);
    }

    public List<Card> getListCardResult(boolean isPoint) {
        List<Card> cards = new ArrayList<>();
        for (Map<Card, Boolean> m : finalChoiceCards) {
            List<Card> tempKey = new ArrayList<>(m.keySet());
            Card c = tempKey.get(0);
            boolean k = m.get(c);
            if (k == isPoint) {
                cards.add(c);
            }
        }
        return cards;
    }

    public ResultStatus answer(List<Card> cards, StochasticGradientDescent sgd) {
        double[] coefficients = sgd.getCoefficients();
        System.out.println("\n\n\n");

        List<Double> info = new ArrayList<>();
        for (Card c : cards) {
            info.add(c.getValue() * 1.0);
        }

        double sum = coefficients[0];
        for (int i = 0; i < coefficients.length - 1; i++) {
            sum += info.get(i) * coefficients[i + 1];
        }
        return CardInHand.getResult(Math.round(sum));
    }

    private static int remainerOf10(int value) {
        return (value != 10) ? (value % 10) : value;
    }
}
