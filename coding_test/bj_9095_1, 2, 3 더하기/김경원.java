import java.io.*;

public class Main {

	public static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < testCase; i++) {
			int n = Integer.parseInt(br.readLine());
			
			dp = new int[n + 1];
			dp[0] = 1;
			System.out.println(func(n));
		}

	}

	public static int func(int n) {
		
		if ( n < 0 )
			return 0;
		
		if ( dp[n] != 0 )
			return dp[n];
		
		dp[n] = func(n - 1) + func(n - 2) + func(n - 3);
		
		return dp[n];
	}
	
}
