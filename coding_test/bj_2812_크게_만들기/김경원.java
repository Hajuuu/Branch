import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();

		String str = br.readLine();
		char[] c = str.toCharArray();

		for (int i = 0; i < N; i++)
			q.offer(c[i] - '0');

		func(q, N - K);

	}

	public static void func(Queue<Integer> q, int size) {
		
		Deque<Integer> rslt = new LinkedList<>();
		
		while ( !q.isEmpty() ) {
			int top = q.poll();
			
			while ( !rslt.isEmpty() && rslt.peekLast() < top ) {
				
				int d_remain = size - (rslt.size() - 1);
				int q_remain = q.size() + 1;
				
				if ( d_remain <= q_remain)
					rslt.removeLast();
				else
					break;
			}
			
			if ( rslt.size() < size)
				rslt.addLast(top);
			
		}
		
		while ( !rslt.isEmpty()) {
			System.out.print(rslt.poll());
		}
		
		
	}
	
}
