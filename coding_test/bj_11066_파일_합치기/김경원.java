import java.io.*;
import java.util.*;

public class Main {

	public static int[] arr;
	public static int[][] dp;
	public static int[][] file;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int K = Integer.parseInt(br.readLine());
			
			arr = new int[K];
			dp = new int[K][K];
			file = new int[K][K];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < K; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int k = 0; k < K; k++) {
				int sum = 0;
				for(int w = k; w < K; w++) {
					sum += arr[w];
					
					file[k][w] = sum;
				}
			}
			
			func();
		
			System.out.println(dp[0][dp.length - 1]);
		}
	}

	public static void func() {
		
		for(int j = 0; j < dp.length; j++) {
			for(int i = j; i >= 0; i--) {
				
				if ( i == j )
					continue;
				
				if ( Math.abs(i - j) == 1 ) {
					dp[i][j] = Math.abs(arr[i] + arr[j]);
					continue;
				}
				
				int min = Integer.MAX_VALUE;
				
				for(int k = 0; k < j - i; k++) {
					int a_cost = dp[i][i + k];
					int b_cost = dp[i + k + 1][j];
					int total_file_size = file[i][j];

					min = Math.min(min, a_cost + b_cost + total_file_size);
				}
				
				dp[i][j] = min;
			}
		}
		
	}
}
