import data.ExpUtils;
import domain.*;
import stochastic_gradient_descent.StochasticGradientDescent;

import java.util.ArrayList;
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
        fList1.add(new Card(3, Type.SPADE));
        fList1.add(new Card(2, Type.CLUBS));
        fList1.add(new Card(6, Type.SPADE));
        fList1.add(new Card(2, Type.DIAMONDS));
        fList1.add(new Card(8, Type.SPADE));
        fList1.add(new Card(9, Type.DIAMONDS));

        CardInHand mInfor1 = new CardInHand(ResultStatus.ADD, fList1);
        mVote.add(mInfor1);

        List<Card> fList2 = new ArrayList<>();
        fList2.add(new Card(6, Type.CLUBS));
        fList2.add(new Card(5, Type.DIAMONDS));
        fList2.add(new Card(1, Type.HEARTS));
        fList2.add(new Card(8, Type.SPADE));
        fList2.add(new Card(2, Type.CLUBS));
        fList2.add(new Card(4, Type.SPADE));
        fList2.add(new Card(7, Type.DIAMONDS));
        fList2.add(new Card(1, Type.SPADE));
        fList2.add(new Card(9, Type.DIAMONDS));

        CardInHand mInfor2 = new CardInHand(ResultStatus.FOLLOW, fList2);
        mVote.add(mInfor2);

        List<Card> fList3 = new ArrayList<>();
        fList3.add(new Card(9, Type.CLUBS));
        fList3.add(new Card(7, Type.DIAMONDS));
        fList3.add(new Card(1, Type.HEARTS));
        fList3.add(new Card(2, Type.SPADE));
        fList3.add(new Card(5, Type.CLUBS));
        fList3.add(new Card(6, Type.SPADE));
        fList3.add(new Card(1, Type.DIAMONDS));
        fList3.add(new Card(9, Type.SPADE));
        fList3.add(new Card(4, Type.DIAMONDS));

        CardInHand mInfor3 = new CardInHand(ResultStatus.DENY, fList3);
        mVote.add(mInfor3);

        List<Card> fList4 = new ArrayList<>();
        fList4.add(new Card(1, Type.CLUBS));
        fList4.add(new Card(2, Type.DIAMONDS));
        fList4.add(new Card(3, Type.HEARTS));
        fList4.add(new Card(3, Type.SPADE));
        fList4.add(new Card(4, Type.CLUBS));
        fList4.add(new Card(5, Type.SPADE));
        fList4.add(new Card(6, Type.DIAMONDS));
        fList4.add(new Card(8, Type.SPADE));
        fList4.add(new Card(7, Type.DIAMONDS));

        CardInHand mInfor4 = new CardInHand(ResultStatus.ADD_ALL, fList4);
        mVote.add(mInfor4);

        List<Card> fList5 = new ArrayList<>();
        fList5.add(new Card(4, Type.CLUBS));
        fList5.add(new Card(5, Type.DIAMONDS));
        fList5.add(new Card(1, Type.HEARTS));
        fList5.add(new Card(3, Type.SPADE));
        fList5.add(new Card(2, Type.CLUBS));
        fList5.add(new Card(6, Type.SPADE));
        fList5.add(new Card(2, Type.DIAMONDS));
        fList5.add(new Card(8, Type.SPADE));
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

        double[] xTrain = {5, 2, 3, 1, 2, 5, 7, 8, 9};
        double sum = coefficients[0];
        for (int i = 0; i < coefficients.length - 1; i++) {
            sum += xTrain[i] * coefficients[i + 1];
        }
        System.out.println(CardInHand.getResult(Math.round(sum)));

        // createData();

        List<Card> lCard = new ArrayList<>();
        lCard.add(new Card(5, Type.SPADE));
        lCard.add(new Card(3, Type.HEARTS));
        lCard.add(new Card(2, Type.DIAMONDS));
        lCard.add(new Card(8, Type.SPADE));
        lCard.add(new Card(7, Type.CLUBS));
        CardInHand cIH = new CardInHand(ResultStatus.FOLLOW, lCard);
        Player p = new Player("Huy", cIH);

        List<Map<Card, Boolean>> finalChoiceCards = p.execute();
        System.out.println("Done !!!");
    }
}
