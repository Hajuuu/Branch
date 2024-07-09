import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[] dx = {0, -1, 1, 0}; // 상좌우하
    static int[] dy = {-1, 0, 0, 1};
    static int bx, by;
    static int result = 0; // 총 걸린 시간
    static int count = 0; // 물고기 먹은 횟수
    static int sz = 2; // 상어 사이즈
    static boolean stop = false; // 먹을 물고기가 없는 경우
    static boolean eat = false; // 한 마리를 먹은 경우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    by = i; // 상어 시작 위치
                    bx = j;
                    map[i][j] = 0;
                }
            }
        }

        while (!stop) {
            boolean[][] visit = new boolean[n][n];
            bfs(bx, by, visit, sz);
            if (eat) { // 먹었을 경우
                eat = false;
                count += 1; // 현재 크기에서 물고기 먹은 횟수 증가
                map[by][bx] = 0; // 먹은 물고기 삭제
                if (count == sz) { // 상어 크기가 증가하는 경우
                    sz += 1; // 상어 사이즈 +1
                    count = 0; //현재 크기에서 물고기 먹은 횟수 초기화
                }
            } else { // 한 마리도 못먹는 경우
                stop = true; // 도움!
            }
        }
        System.out.println(result);
    }

    static void bfs(int a, int b, boolean[][] visit, int shSize) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, b, 0});
        visit[b][a] = true;
        int temp = 0; // 한 마리를 먹는데 걸린 시간

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0]; // 열 좌표
            int y = cur[1]; // 행 좌표
            int cnt = cur[2]; // 현재 시간

            // 가장 위쪽을 먼저 그 다음 왼쪽을 우선적으로 먹는 것을 고려
            if (map[y][x] > 0 && map[y][x] < shSize && temp == cnt) {
                if ((by > y) || (by == y && bx > x)) {
                    by = y; // 물고기를 먹은 상어 위치 저장
                    bx = x;
                    continue;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 방향 이동
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visit[ny][nx]) {
                    if (map[ny][nx] <= shSize) { // 지나가거나 먹을 수 있는 경우
                        if (map[ny][nx] > 0 && map[ny][nx] < shSize && !eat) { //물고기를 먹을 수 있는 경우
                            eat = true; // 한 마리 먹음
                            bx = nx; //시작 위치를 물고기를 먹었던 위치로
                            by = ny;
                            temp = cnt + 1; // 한 마리 먹는데걸린 시간
                            result += temp; // 총 시간에 추가
                        } else { // 물고기를 못먹는 경우
                            q.offer(new int[]{nx, ny, cnt + 1});
                            visit[ny][nx] = true;
                        }
                    }
                }
            }
        }
    }
}
