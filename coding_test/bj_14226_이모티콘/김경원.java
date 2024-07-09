import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[][] visited = new boolean[1001][1001];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int S = Integer.parseInt(br.readLine());
		
		System.out.println(func(S));
	}
	
	public static int func(int S) {
		
		Queue<int[]> q = new LinkedList<>(); // 화면 이모티콘 수, 클립보드 복사된 이모티콘 수, 시간 순으로 저장
		q.offer(new int[] {1, 0, 0});
		visited[0][1] = true; 
		
		int view = q.peek()[0];
		int clipboard = q.peek()[1];
		int time = q.peek()[2];
		
		while ( !q.isEmpty() ) {
			
			if ( view == S )
				return time;
			
			int[] arr = q.poll();
			
			view = arr[0];
			clipboard = arr[1];
			time = arr[2];
			
			q.offer(new int[] {view, view, time + 1}); // 클립보드에 화면 이모티콘을 복사
			
			if ( clipboard != 0 && view + clipboard <= S && !visited[clipboard][view + clipboard]) {
				q.offer(new int[] {view + clipboard, clipboard, time + 1}); // 클립보드에 저장된 이모티콘을 화면에 붙여넣기
				visited[clipboard][view + clipboard] = true;
			}
			
			if ( view >= 1 && !visited[clipboard][view - 1]) {
				q.offer(new int[] {view - 1, clipboard, time + 1}); // 화면에 이모티콘 하나 삭제
				visited[clipboard][view - 1] = true;
			}
			
		}
		
		return time;
		
	}
	
}
