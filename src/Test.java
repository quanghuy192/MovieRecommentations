import data.ExpUtils;
import domain.*;
import stochastic_gradient_descent.StochasticGradientDescent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {

    /*public static void main(String[] args) {
        double[] coefficients = {0.4, 0.8};
        double[][] dataset = {
                {1.0, 1.0},
                {2.0, 3.0},
                {4.0, 3.0},
                {3.0, 2.0},
                {5.0, 5.0}
        };
        double[] newcoef = coef_sgd(dataset, 0.001, 500);
        System.out.println(newcoef[0] + " " + newcoef[1]);
    }

    private static double[] coef_sgd(double[][] train, double l_rate, double n_epoch) {
        double[] coefficients = {0.0, 0.0};
        for (int e = 0; e < n_epoch; e++) {
            double sum_error = 0.0;
            for (int w = 0; w < train.length; w++) {
                double yhat = predict(train[w], coefficients);
                double error = yhat - train[w][1];
                sum_error += error * error;
                coefficients[0] = coefficients[0] = l_rate * error;
                for (int k = 0; k < (train[w].length - 1); k++) {
                    coefficients[k + 1] = coefficients[k + 1] - l_rate * error * train[w][k];
                }
            }
            System.out.println("b0 = " + coefficients[0] + " b1 = " + coefficients[1]);
            System.out.println("EPOCH " + e + " LRATE " + l_rate + " ERR " + sum_error);
        }
        return coefficients;
    }

    private static double predict(double[] row, double[] coef) {
        double yhat = coef[0];
        for (int i = 0; i < (coef.length - 1); i++) {
            yhat += coef[i + 1] * row[i];
        }
        return yhat;
    }*/

    public static void createData() {
        List<CardInHand> mVote = new ArrayList<>();

        List<Card> fList1 = new ArrayList<>();
        fList1.add(new Card(4, Type.CLUBS));
        fList1.add(new Card(5, Type.DIAMONDS));
        fList1.add(new Card(1, Type.HEARTS));
        fList1.add(new Card(3, Type.SPADES));
        fList1.add(new Card(2, Type.CLUBS));
        fList1.add(new Card(6, Type.SPADES));
        fList1.add(new Card(2, Type.DIAMONDS));
        fList1.add(new Card(8, Type.SPADES));
        fList1.add(new Card(9, Type.DIAMONDS));

        CardInHand mInfor1 = new CardInHand(ResultStatus.ADD, fList1);
        mVote.add(mInfor1);

        List<Card> fList2 = new ArrayList<>();
        fList2.add(new Card(6, Type.CLUBS));
        fList2.add(new Card(5, Type.DIAMONDS));
        fList2.add(new Card(1, Type.HEARTS));
        fList2.add(new Card(8, Type.SPADES));
        fList2.add(new Card(2, Type.CLUBS));
        fList2.add(new Card(4, Type.SPADES));
        fList2.add(new Card(7, Type.DIAMONDS));
        fList2.add(new Card(1, Type.SPADES));
        fList2.add(new Card(9, Type.DIAMONDS));

        CardInHand mInfor2 = new CardInHand(ResultStatus.FOLLOW, fList2);
        mVote.add(mInfor2);

        List<Card> fList3 = new ArrayList<>();
        fList3.add(new Card(9, Type.CLUBS));
        fList3.add(new Card(7, Type.DIAMONDS));
        fList3.add(new Card(1, Type.HEARTS));
        fList3.add(new Card(2, Type.SPADES));
        fList3.add(new Card(5, Type.CLUBS));
        fList3.add(new Card(6, Type.SPADES));
        fList3.add(new Card(1, Type.DIAMONDS));
        fList3.add(new Card(9, Type.SPADES));
        fList3.add(new Card(4, Type.DIAMONDS));

        CardInHand mInfor3 = new CardInHand(ResultStatus.DENY, fList3);
        mVote.add(mInfor3);

        List<Card> fList4 = new ArrayList<>();
        fList4.add(new Card(1, Type.CLUBS));
        fList4.add(new Card(2, Type.DIAMONDS));
        fList4.add(new Card(3, Type.HEARTS));
        fList4.add(new Card(3, Type.SPADES));
        fList4.add(new Card(4, Type.CLUBS));
        fList4.add(new Card(5, Type.SPADES));
        fList4.add(new Card(6, Type.DIAMONDS));
        fList4.add(new Card(8, Type.SPADES));
        fList4.add(new Card(7, Type.DIAMONDS));

        CardInHand mInfor4 = new CardInHand(ResultStatus.ADD_ALL, fList4);
        mVote.add(mInfor4);

        List<Card> fList5 = new ArrayList<>();
        fList5.add(new Card(4, Type.CLUBS));
        fList5.add(new Card(5, Type.DIAMONDS));
        fList5.add(new Card(1, Type.HEARTS));
        fList5.add(new Card(3, Type.SPADES));
        fList5.add(new Card(2, Type.CLUBS));
        fList5.add(new Card(6, Type.SPADES));
        fList5.add(new Card(2, Type.DIAMONDS));
        fList5.add(new Card(8, Type.SPADES));
        fList5.add(new Card(9, Type.DIAMONDS));

        CardInHand mInfor5 = new CardInHand(ResultStatus.ADD, fList5);
        mVote.add(mInfor5);

        ExpUtils utils = new ExpUtils();
        for (CardInHand c : mVote) {
            utils.writeDataTrainning(c);
        }
    }

    public static void main(String[] args) {
        ExpUtils utils = new ExpUtils();
        List<CardInHand> mVote = utils.readDataTrainning();
        utils.closeReader();

        StochasticGradientDescent sgd = new StochasticGradientDescent(mVote);

        double[] coefficients = sgd.getCoefficients();
        System.out.println("\n\n\n");

        double[] xTrain = {5, 14, 48, 23, 24, 22, 7, 8, 9};
        double sum = coefficients[0];
        for (int i = 0; i < coefficients.length - 1; i++) {
            sum += xTrain[i] * coefficients[i + 1];
            System.out.println("xTrain[" + i + "] = " + xTrain[i] + " * " + "coefficients[" + (i + 1) + "] = " + xTrain[i] * coefficients[i + 1]);
        }
        System.out.println(Math.round(sum));
        // System.out.println(CardInHand.getResult(Math.round(sum)));

        // createData();

        Deck deck = new Deck();
        deck.shuffleDeck();

        // Player receives his cards
        List<Card> p1Card = new ArrayList<>();
        List<Card> p2Card = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            p1Card.add(deck.getCardFromDeck());
            p2Card.add(deck.getCardFromDeck());
        }

        // Player computation
        Player enemy = new Player("Enemy", new CardInHand(ResultStatus.FOLLOW, p1Card));
        Player you = new Player("You", new CardInHand(ResultStatus.FOLLOW, p2Card));

        CardInHand cIH = new CardInHand(ResultStatus.FOLLOW, p1Card);
        enemy.execute();
        List<Card> resultCardFor10 = enemy.getListCardResult(true);

        System.out.println("\n\n\n\n\n");
        System.out.println("=================================");

        if (resultCardFor10 != null && resultCardFor10.size() > 2) {
            Card enemyCardResult = enemy.showCard();
            System.out.println(" Your enemy cards with 10 is:");
            for (Card c : resultCardFor10) {
                System.out.print(c.getValue() + "" + c.getType() + " -- ");
            }
            System.out.println("");
            System.out.println(" Your enemy cards with 10 is: " + enemyCardResult.getValue() + enemyCardResult.getType());
            System.out.println("And your Cards list:");
            for (Card c : p1Card) {
                System.out.print(c.getValue() + "" + c.getType() + " -- ");
            }
        } else {
            for (Card c : p2Card) {
                System.out.print(c.getValue() + "" + c.getType() + " -- ");
            }
            System.out.println("You win. Your enemy doesn't have valid card.");
        }
    }
}
