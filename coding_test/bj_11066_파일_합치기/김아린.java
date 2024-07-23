import java.util.*;
import java.io.*;

public class Main {
    static int K;
    static int[] novel;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int testcase = 0; testcase < T; testcase++) {
            K = Integer.parseInt(br.readLine());
            
            novel = new int[K];
            int[] sum = new int[K + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1;j <= K; j++) {
    			sum[j] = sum[j-1] + Integer.parseInt(st.nextToken());
    		}
            //dp[i][j]는 i번 페이지부터 j번 페이지까지 페이지를 합한 최솟값
            //점화식 세우는게 제일 어렵다
            int[][] dp = new int[K+1][K+1];
            
            for(int gap=1; gap<K; gap++) {
                for(int start = 1; start+gap <= K; start++) {
                    int end = start + gap;
                    dp[start][end] = Integer.MAX_VALUE;

                    for(int mid = start; mid<end ;mid++) {
                        dp[start][end] = Math.min(dp[start][end],dp[start][mid]+dp[mid+1][end]+sum[end]-sum[start-1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}
