import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] map, dp;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];
		PriorityQueue<Point> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], 1);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				pq.offer(new Point(i, j, map[i][j]));
			}
		}

		while (!pq.isEmpty()) {
			Point point = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] <= map[point.x][point.y])
					continue;
				dp[nx][ny] = Math.max(dp[nx][ny], dp[point.x][point.y] + 1);
			}
		}

		int max = Arrays.stream(dp)
				.flatMapToInt(Arrays::stream)
					.max()
						.getAsInt();

		System.out.println(max);
	}
}

class Point implements Comparable<Point> {
	int x, y;
	int w;

	public Point(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public int compareTo(Point o) {
		return this.w - o.w;
	}
}