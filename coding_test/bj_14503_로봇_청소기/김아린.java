import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static final int MAX = 50;
    static int N, M;
    static int visitedCount = 0;
    static int[][] map = new int[MAX][MAX];
    static int[][] visited = new int[MAX][MAX];

    // 북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int r, c, d;

    public static void main(String[] args) throws IOException {
        Input();
        dfs();
    }

    static void Input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        
        line = br.readLine().split(" ");
        r = Integer.parseInt(line[0]);
        c = Integer.parseInt(line[1]);
        d = Integer.parseInt(line[2]);

        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        visited[r][c] = 1;
        visitedCount++;
    }

    static void dfs() {
        // 왼쪽으로 회전
        for (int i = 0; i < 4; i++) {
            int nextD = (d + 3 - i) % 4; // next direction (왼쪽)
            int nextR = r + dx[nextD];
            int nextC = c + dy[nextD];

            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || map[nextR][nextC] == 1) {
                continue;
            }

            if (map[nextR][nextC] == 0 && visited[nextR][nextC] == 0) {
                visited[nextR][nextC] = 1;
                r = nextR;
                c = nextC;
                d = nextD;
                visitedCount++;
                dfs();
                return;
            }
        }

        int backIdx = d > 1 ? d - 2 : d + 2;
        int backR = r + dx[backIdx];
        int backC = c + dy[backIdx];
      
        if (backR >= 0 && backR < N && backC >= 0 && backC < M) {
            if (map[backR][backC] == 0) {
                r = backR;
                c = backC;
                dfs();
            }
            else {
                System.out.println(visitedCount);
                System.exit(0);
            }
        }
    }
}
