import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] map;
    static Set<Integer> set = new HashSet<>();
    static int result;
    static boolean[] visited, finished;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            
            StringTokenizer st = null;
            st = new StringTokenizer(br.readLine());
            map = new int[100001];
            visited = new boolean[100001];
            finished = new boolean[100001];
            
            for (int i = 1; i <= n; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }
            
            result = 0;
            for (int i = 1; i <= n; i++) {
                if(visited[i])
                    continue;
                dfs(i);
            }
            
            System.out.println(n - result);
        }
    }

  // 사이클 결정과 방문 처리 두가지를 해줘야 함
  // 참고함!
    static void dfs(int cur) {
        // 방문 처리
        visited[cur] = true;
        // 다음에 방문할 노드
        int next = map[cur];

        // 다음 노드를 아직 방문하지 않았다면 방문 수행
        if(!visited[next]){
            dfs(next);
        }

        // cur이 갖고 있는 값을 방문하였는데 노드 끝이 아니라면 cycle
        if(visited[next] && !finished[next]){
            // cycle 내의 학생 수 더해주기
            for(int temp = next ; temp != cur ; temp = map[temp])
                result++;
            // 자기 자신
            result ++;
        }

        finished[cur] = true;
    }
}
