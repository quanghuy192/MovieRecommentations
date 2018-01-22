package gradient_descent;

import java.util.List;

public class StochasticGradientDescent {

    private final double ALPHA = 0.01;
    private List<MovieInfor> friendVoteList;
    private double[] beta;

    int[] y;
    int[][] X;

    public StochasticGradientDescent(List<MovieInfor> fVoteList) {
        this.friendVoteList = fVoteList;
        int size = friendVoteList.size();

        // create data
        beta = new double[size];
        for (int i = 0; i < size; i++) {
            beta[i] = 0.0d;
        }

        y = new int[size];
        X = new int[size][size];
        int count = 0;
        List<Friend> fList;

        for (MovieInfor f : friendVoteList) {
            y[count] = f.isLike();
            fList = f.getFriendVoteList();
            int[] x = new int[fList.size()];
            int j = 0;

            for (Friend friend : fList) {
                x[j] = friend.getVote();
                j++;
            }

            X[count] = x;
            count++;
        }

        learn(50);
        System.out.println("Result --------------------- ");
        for (int s = 0; s < beta.length; s++) {
            System.out.print(" " + beta[s]);
        }

    }

    private void learn(int n_EPOCH) {
        while (n_EPOCH > 0) {
            double sumError = 0.0;
            for (int h = 0; h < X.length; h++) {
                double predicate = beta[0] * 1;
                for (int k = 1; k < beta.length; k++) {
                    predicate += beta[k] * X[h][k - 1];
                }
                double error = predicate - y[0];
                sumError += error * error;
                beta[0] = beta[0] - ALPHA * error;

                for (int j = 1; j < beta.length; j++) {
                    beta[j] = beta[j] - ALPHA * error * X[h][j - 1];
                }
            }
            for (int s = 0; s < beta.length; s++) {
                System.out.print(" " + beta[s]);
            }
            System.out.println();
            n_EPOCH--;
            learn(n_EPOCH);
        }
    }
    /*

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
}
