import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int dist = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			int max = operate(i);
			if (max != Integer.MAX_VALUE) {
				dist = Math.min(dist, max - i);
			}
		}

		System.out.println(dist);
	}

	static int operate(int min) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] < min) {
					map[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		int[][] dp = new int[n][n];
		dp[0][0] = map[0][0];
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], map[i][0]);
			dp[0][i] = Math.max(dp[0][i - 1], map[0][i]);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.max(Math.min(dp[i - 1][j], dp[i][j - 1]), map[i][j]);
			}
		}

		return dp[n - 1][n - 1];
	}
}