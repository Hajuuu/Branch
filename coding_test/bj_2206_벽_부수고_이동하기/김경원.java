import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int N;
	static int M;
	
	static int[][] arr;
	static boolean[][][] visited;
	
	public static class Point {
		int x;
		int y;
		int dist;
		boolean flag;
		
		public Point(int x, int y, int dist, boolean flag) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.flag = flag;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i = 0; i < N; i++) {
			
			String str = br.readLine();
			
			for(int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
	}
	
	public static int bfs() {
	
		Queue<Point> q = new LinkedList<>();
		
		int x = 0;
		int y = 0;
		int dist = 1;
		boolean flag = false;
		
		q.add(new Point(x, y, dist, flag));
		visited[x][y][0] = true;
		
		while (!(q.isEmpty() && flag == true)) {
			Point p = q.poll();
			
			x = p.x;
			y = p.y;
			dist = p.dist;
			flag = p.flag;
			
			if ( x == N - 1 && y == M - 1 )
				return dist;
			
			for(int i = 0; i < 4; i++) {
				
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if ( nextX < 0 || nextX > arr.length - 1 || nextY < 0 || nextY > arr[0].length - 1) // 범위 벗어난 경로면
					continue;
				
				if ( flag == true ) {
					if ( visited[nextX][nextY][1] )
						continue;
				} else {
					if ( visited[nextX][nextY][0] )
						continue;
				}
				
				if ( arr[nextX][nextY] == 1 && flag == false) { // 한 번 깨부술 수 있는데, 마침 벽일경우
					q.offer(new Point(nextX, nextY, dist + 1, true));
					visited[nextX][nextY][1] = true;
					continue;
				} 
				
				if ( arr[nextX][nextY] == 0 ) { // 갈 수 있는 경로일 경우
					
					if ( flag == true ) { // 이전에 이미 한 번 벽을 깨부수고 온 경우
						q.offer(new Point(nextX, nextY, dist + 1, true));
						visited[nextX][nextY][1] = true;
					} else {
						q.offer(new Point(nextX, nextY, dist + 1, false));
						visited[nextX][nextY][0] = true;
					}
				}
			}
		}
	
		return -1;
	}
	
}
