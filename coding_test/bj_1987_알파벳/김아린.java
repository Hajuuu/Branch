import java.util.*;
import java.io.*;

public class Main {
    static int R, C, ans, cnt;
    static char[][] map;
    //static boolean[][] visited;
    static Set<Character> set = new HashSet<>();
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        ans = 0;
        cnt = 0;
        
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }
        
        //visited = new boolean[R][C];
        //visited[0][0] = true;
        set.add(map[0][0]);
        
        backtracking(0, 0);
        
        System.out.println(ans);
    }
    
    static void backtracking(int x, int y) {
        cnt++;
        ans = Math.max(cnt, ans);
        
        for (int i = 0; i < 4; i++) {
            int nx = dir[i][0] + x;
            int ny = dir[i][1] + y;
            
            if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            //if(visited[nx][ny]) continue;
            if(set.contains(map[nx][ny])) continue;
            
            //visited[nx][ny] = true;
            set.add(map[nx][ny]);
            
            backtracking(nx, ny);
            
            //visited[nx][ny] = false;
            set.remove(map[nx][ny]);
            cnt--;
        }
        return;
    }
}
