import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[][] arr, dp;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[M + 1][N + 1]; // (x, y)에서 도착점으로 가는 경로의 개수
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = -1;
			}
		}

		bw.write(DFS(1, 1) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static int DFS(int x, int y) {
		if (x == M && y == N) {
			return 1;
		}

		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		dp[x][y] = 0; // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화.
		for (int i = 0; i < 4; i++) {
			int rr = x + dx[i];
			int cc = y + dy[i];

			if (rr < 1 || cc < 1 || rr > M || cc > N) {
				continue;
			}
			
			// arr[x][y]보다 arr[dx][dy]가 높이가 더 낮다면
			// arr[dx][dy]에서 끝점까지 도달하는 경로의 개수를 더한다.
			if (arr[x][y] > arr[rr][cc]) {
				dp[x][y] += DFS(rr, cc);
			}
		}

		return dp[x][y];
	}

}
