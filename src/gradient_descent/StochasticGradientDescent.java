package gradient_descent;

import java.util.List;
import java.util.Random;

public class StochasticGradientDescent {

	private final int ALPHA = 1;
	private List<MovieInfor> friendVoteList;
	private double[] beta, resultBeta;

	int[] y;
	int[][] X;

	private Random r = new Random();

	public StochasticGradientDescent(List<MovieInfor> friendVoteList) {
		this.friendVoteList = friendVoteList;
		int size = friendVoteList.size();

		// create data
		beta = new double[size];
		resultBeta = new double[size];
		for (int i = 0; i < size; i++) {
			beta[i] = r.nextDouble();
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
	}

	public void learn(int i) {
		for (int j = 0; j < beta.length; j++) {
			double delta = ALPHA * (y[i] - beta[i] * X[i][j]);
		}
	}
}
