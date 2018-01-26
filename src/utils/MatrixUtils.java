package utils;

public class MatrixUtils {

	private MatrixUtils() {
	}

	public static int multi(int[][] Xij, int[] Yi) {
		if (Xij.length != Yi.length) {
			return -1;
		}
		int sizeX = Xij.length;
		int sum = 0;
		for (int i = 0; i < sizeX; i++) {
			sum += multi(Xij[i], Yi);
		}
		return sum;
	}

	public static int multi(int[] Xi, int[] Yi) {
		if (Xi.length != Yi.length) {
			return -1;
		}
		int size = Yi.length;
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum += Xi[i] * Yi[i];
		}
		return sum;
	}
}
