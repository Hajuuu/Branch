import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class bpj_2812 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		Deque<Integer> queue = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			int now = str.charAt(i) - '0';
			while(K > 0 && !queue.isEmpty() && queue.getLast() < now) {
				queue.removeLast();
				K--;
			}
			queue.addLast(now);
		}

		StringBuilder sb = new StringBuilder();
		while(queue.size() > K) {
			sb.append(queue.removeFirst());
		}
		
		System.out.println(sb);
	}
	

}
