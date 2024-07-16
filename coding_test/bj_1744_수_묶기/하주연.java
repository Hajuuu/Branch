import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_1744 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
			if(o2 <= 0 && o1 <= 0) {
				return Math.abs(o2) - Math.abs(o1);
			}
			return o2 - o1;
		});
		for(int i = 0; i < N; i++) {
			queue.offer(Integer.parseInt(br.readLine()));
		}
		int answer = 0;
		while(queue.size() > 1) {
			int n1 = queue.poll();
			int n2 = queue.poll();
			if(n1 * n2 + answer > n1 + n2 + answer) {
				answer += n1 * n2;
			} else {
				answer += n1;
				queue.offer(n2);
			}
		}
		if(!queue.isEmpty()) {
			answer += queue.poll();
		}
		System.out.println(answer);
	}

}
