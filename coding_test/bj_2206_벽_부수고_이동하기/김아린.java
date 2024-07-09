import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map [i][j] = Integer.valueOf(row.charAt(j) - '0');
            }
        }
        
        bfs(0,0);
    }
    
    static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2]; // 0: 벽 X, 1: 벽 O
        q.add(new int[]{x, y, 0, 1}); // x, y, 부수고 이동한 여부, 거리
        visited[x][y][0] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            if(now[0] == N-1 && now[1] == M-1) {
                System.out.println(now[3]);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int cx = now[0] + dir[i][0];
                int cy = now[1] + dir[i][1];
                int cnt = now[2];
                int sum = now[3];
                
                if(cx < 0 || cy < 0 || cx >=N || cy >= M) continue;
                
                if(map[cx][cy] == 1) { 
                    if(cnt == 0) { // 벽을 부술 기회가 남은 경우
                        if(!visited[cx][cy][1]) {
                            visited[cx][cy][1] = true;
                            q.add(new int[]{cx, cy, 1, sum+1});
                        }
                    }
                } else {
                    if(!visited[cx][cy][cnt]) {
                        visited[cx][cy][cnt] = true;
                        q.add(new int[]{cx, cy, cnt, sum+1});
                    }
                }
            }
        }
        
        System.out.println(-1);
    }
}
