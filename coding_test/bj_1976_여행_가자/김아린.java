import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static boolean[] visited;
    static int[] route;

    // BFS로 접근
    static boolean BFS(int start, int dest) {
        Queue<Integer> q = new ArrayDeque<>();
        visited = new boolean[N + 1];
        visited[start] = true;
        q.add(start);
        
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int qp = q.poll();
                if (qp == dest) // 경로가 있으면? 탈출
                    return true;

                for (int j = 1; j <= N; j++) {
                    if (graph[qp][j] != 0 && !visited[j]) {
                        q.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
        return false;
    }

    static boolean isOkay(int[] route) {
        for (int i = 0; i < route.length - 1; i++) {
            if (!BFS(route[i], route[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        graph = new int[N + 1][N + 1];
        route = new int[M];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }
        
        // 여행 계획 검증
        if (isOkay(route)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
