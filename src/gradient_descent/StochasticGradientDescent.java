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
        int sizeTrain = friendVoteList.size();
        int sizeAttr = 0;

        if (friendVoteList.get(0) != null) {
            sizeAttr = friendVoteList.get(0).getFriendVoteList().size();
        }


        // create data
        beta = new double[sizeAttr + 1];
        for (int i = 0; i < sizeAttr + 1; i++) {
            beta[i] = 0.0d;
        }

        y = new int[sizeTrain];
        X = new int[sizeTrain][sizeAttr];
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

        learn(1000);
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
                for (int k = 0; k < beta.length - 1; k++) {
                    predicate += beta[k + 1] * X[h][k];
                }
                double error = predicate - y[h];
                sumError += error * error;
                beta[0] = ALPHA * error;

                for (int j = 0; j < beta.length - 1; j++) {
                    beta[j + 1] = beta[j + 1] - ALPHA * error * X[h][j];
                }
            }
            for (int s = 0; s < beta.length; s++) {
                System.out.print(" " + beta[s] + " ");
            }
            System.out.println(" sum error = " + sumError);
            System.out.println();
            n_EPOCH--;
        }
    }

    public double[] getCoefficients() {
        return beta;
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
