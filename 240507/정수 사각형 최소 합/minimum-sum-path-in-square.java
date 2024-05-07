import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int n;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		}
		dp[0][n-1] = map[0][n-1];
		for (int i = n-2; i >=0 ; i--) {
			dp[0][i] = dp[0][i + 1] + map[0][i];
		}

		for (int i = 1; i < n; i++) {
			dp[i][n - 1] = dp[i - 1][n - 1] + map[i][n-1];
		}

		for (int i = 1; i < n; i++) {
			for (int j = n-2; j >= 0; j--) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j + 1]) + map[i][j];
			}
		}

		System.out.println(dp[n - 1][0]);
	}
}