import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class boj_1700 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> useList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			useList.add(new int[] {Integer.parseInt(st.nextToken()), i});
		}

		int[] multitap = new int[N];
		int count = 0;
		for(int i = 0; i < K; i++) {
			int[] now = useList.get(i);
			if(IntStream.of(multitap).anyMatch(x -> x == now[0])) {
				continue;
			}
			if(IntStream.of(multitap).anyMatch(x -> x == 0)) {
				for(int j = 0; j < N; j++) {
					if(multitap[j] == 0) {
						multitap[j] = now[0];
						break;
					}
				}
				continue;
			}
			int max = Integer.MIN_VALUE;
			int select = 0;
			for(int j = 0; j < N; j++) {
				int[] index = find(useList, multitap[j], i);
				if(index[1] == 0) {
					select = j;
					break;
				}
				if(max < index[1]) {
					max = index[1];
					select = j;
				}
				
			}
			
			
			multitap[select] = now[0];
			count++;
		}
		
		System.out.println(count);
		
	}

	public static int[] find(ArrayList<int[]> useList, int num, int k) {
		int[] arr = new int[2];
		for(int i = k + 1; i < useList.size(); i++) {
			if(useList.get(i)[0] == num) {
				arr[0] = useList.get(i)[0];
				arr[1] = i;
				break;
			}
		}
		return arr;
	}
}
