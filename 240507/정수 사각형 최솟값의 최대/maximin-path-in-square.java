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
		dp[0][0] = map[0][0];

		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(dp[i - 1][0], map[i][0]);
			dp[0][i] = Math.min(dp[0][i - 1], map[0][i]);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.min(Math.max(dp[i - 1][j], dp[i][j - 1]), map[i][j]);
			}
		}
		System.out.println(dp[n - 1][n-1]);
	}
}