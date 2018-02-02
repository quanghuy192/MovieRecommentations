package domain;

import java.util.*;

public class Deck {

    private HashMap<Card, Integer> mapCard = new HashMap<>();
    private Stack<Card> deck;

    public Deck() {
        mapCard.put(new Card(1, Type.SPADES), 1);
        mapCard.put(new Card(2, Type.SPADES), 2);
        mapCard.put(new Card(3, Type.SPADES), 3);
        mapCard.put(new Card(4, Type.SPADES), 4);
        mapCard.put(new Card(5, Type.SPADES), 5);
        mapCard.put(new Card(6, Type.SPADES), 6);
        mapCard.put(new Card(7, Type.SPADES), 7);
        mapCard.put(new Card(8, Type.SPADES), 8);
        mapCard.put(new Card(9, Type.SPADES), 9);
        // mapCard.put(new Card(10, Type.SPADE), 10);
        // mapCard.put(new Card(11, Type.SPADE), 11);
        // mapCard.put(new Card(12, Type.SPADE), 12);
        // mapCard.put(new Card(13, Type.SPADE), 13);
        mapCard.put(new Card(1, Type.CLUBS), 14);
        mapCard.put(new Card(2, Type.CLUBS), 15);
        mapCard.put(new Card(3, Type.CLUBS), 16);
        mapCard.put(new Card(4, Type.CLUBS), 17);
        mapCard.put(new Card(5, Type.CLUBS), 18);
        mapCard.put(new Card(6, Type.CLUBS), 19);
        mapCard.put(new Card(7, Type.CLUBS), 20);
        mapCard.put(new Card(8, Type.CLUBS), 21);
        mapCard.put(new Card(9, Type.CLUBS), 22);
        // mapCard.put(new Card(10, Type.CLUBS), 23);
        // mapCard.put(new Card(11, Type.CLUBS), 24);
        // mapCard.put(new Card(12, Type.CLUBS), 25);
        // mapCard.put(new Card(13, Type.CLUBS), 26);
        mapCard.put(new Card(1, Type.DIAMONDS), 27);
        mapCard.put(new Card(2, Type.DIAMONDS), 28);
        mapCard.put(new Card(3, Type.DIAMONDS), 29);
        mapCard.put(new Card(4, Type.DIAMONDS), 30);
        mapCard.put(new Card(5, Type.DIAMONDS), 31);
        mapCard.put(new Card(6, Type.DIAMONDS), 32);
        mapCard.put(new Card(7, Type.DIAMONDS), 33);
        mapCard.put(new Card(8, Type.DIAMONDS), 34);
        mapCard.put(new Card(9, Type.DIAMONDS), 35);
        // mapCard.put(new Card(10, Type.DIAMONDS), 36);
        // mapCard.put(new Card(11, Type.DIAMONDS), 37);
        // mapCard.put(new Card(12, Type.DIAMONDS), 38);
        // mapCard.put(new Card(13, Type.DIAMONDS), 39);
        mapCard.put(new Card(1, Type.HEARTS), 40);
        mapCard.put(new Card(2, Type.HEARTS), 41);
        mapCard.put(new Card(3, Type.HEARTS), 42);
        mapCard.put(new Card(4, Type.HEARTS), 43);
        mapCard.put(new Card(5, Type.HEARTS), 44);
        mapCard.put(new Card(6, Type.HEARTS), 45);
        mapCard.put(new Card(7, Type.HEARTS), 46);
        mapCard.put(new Card(8, Type.HEARTS), 47);
        mapCard.put(new Card(9, Type.HEARTS), 48);
        // mapCard.put(new Card(10, Type.HEARTS), 49);
        // mapCard.put(new Card(11, Type.HEARTS), 50);
        // mapCard.put(new Card(12, Type.HEARTS), 51);
        // mapCard.put(new Card(13, Type.HEARTS), 52);
    }

    public HashMap<Card, Integer> getMapCard() {
        return new HashMap<>(mapCard);
    }

    public int convertCardToOrder(Card card) {
        return mapCard.get(card);
    }

    public void shuffleDeck() {
        List<Card> listCard = new ArrayList<>(mapCard.keySet());
        Collections.shuffle(listCard);
        deck = new Stack<>();
        deck.addAll(listCard);
    }

    public Card getCardFromDeck() {
        if (deck == null || deck.isEmpty()) {
            shuffleDeck();
        }
        return deck.pop();
    }
}
