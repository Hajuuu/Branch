import java.util.*;
import java.io.*;

public class Main {
    static int[][] visited;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        bfs();
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        visited = new int[4001][4001];

        q.add(new int[]{1, 0, 0}); // {화면 이모티콘 개수, 클립보드 이모티콘 개수, 걸린 시간}
        visited[1][0] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int screen = now[0];
            int clip = now[1];
            int time = now[2];

            if (screen == S) {
                System.out.println(time);
                return;
            }

            // 클립보드에 화면 이모티콘 저장
            if (visited[screen][screen] == 0) {
                q.add(new int[]{screen, screen, time + 1});
                visited[screen][screen] = 1;
            }

            // 클립보드에 있는 이모티콘 붙여넣기
            if (clip > 0 && screen + clip < 4001 && visited[screen + clip][clip] == 0) {
                q.add(new int[]{screen + clip, clip, time + 1});
                visited[screen + clip][clip] = 1;
            }

            // 화면에 있는 이모티콘 삭제
            if (screen > 0 && visited[screen - 1][clip] == 0) {
                q.add(new int[]{screen - 1, clip, time + 1});
                visited[screen - 1][clip] = 1;
            }
        }
    }
}
