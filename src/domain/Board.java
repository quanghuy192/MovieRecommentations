package domain;

import data.ExpUtils;
import stochastic_gradient_descent.StochasticGradientDescent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private Player p1;
    private Player p2;
    private Deck deck;

    public Board() {

        // Shuffle Deck
        deck = new Deck();
        deck.shuffleDeck();

        // Player receives his cards
        List<Card> p1Card = new ArrayList<>();
        List<Card> p2Card = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            p1Card.add(deck.getCardFromDeck());
            p2Card.add(deck.getCardFromDeck());
        }

        // Player computation
        p1 = new Player("A", new CardInHand(ResultStatus.FOLLOW, p1Card));
        p2 = new Player("B", new CardInHand(ResultStatus.FOLLOW, p2Card));
    }

    public void play() {
        List<Map<Card, Boolean>> finalChoiceCards1 = p1.execute();
        List<Map<Card, Boolean>> finalChoiceCards2 = p2.execute();

        List<Card> p1CardForLearn = new ArrayList<>();
        List<Card> p2CardForLearn = new ArrayList<>();

        // learn
        ExpUtils utils = new ExpUtils();
        List<CardInHand> data = utils.readDataTrainning();
        utils.closeReader();
        StochasticGradientDescent sgd = new StochasticGradientDescent(data);
        if (finalChoiceCards1 != null && finalChoiceCards2 != null) {

            // P1 see result Card P2
            p1CardForLearn.addAll(p1.getCardInHand().getCardList());
            p1CardForLearn.addAll(p2.getListCardResult(false));
            p1CardForLearn.add(p2.showCard());

            // P2 see result Card P1
            p2CardForLearn.addAll(p1.getCardInHand().getCardList());
            p2CardForLearn.addAll(p1.getListCardResult(false));
            p2CardForLearn.add(p1.showCard());

            // learn
            ResultStatus r1 = p1.answer(p1CardForLearn, sgd);
            ResultStatus r2 = p1.answer(p1CardForLearn, sgd);

            // Get Best Point
            if (p1.showPoint() > p2.showPoint()) {
                utils.writeDataTrainning(new CardInHand(r1, p1CardForLearn));
            } else {
                utils.writeDataTrainning(new CardInHand(r2, p2CardForLearn));
            }
        }
    }
}
