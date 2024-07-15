import java.io.*;
import java.util.*;

public class Main {
	
	public static class Fish implements Comparable<Fish> {
		
		int x;
		int y;
		int dist;
		
		public Fish(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
		public int compareTo(Fish other) { // this가 작으면 음수, 같으면 0, 더 크면 양수 반환
			
			if ( this.dist > other.dist )
				return 1;
			else if ( this.dist < other.dist )
				return -1;
			else {
				if ( this.x > other.x )
					return 1;
				else if ( this.x < other.x )
					return -1;
				else {
					if ( this.y > other.y )
						return 1;
					else
						return -1;
				}
			}
		}
	}
	
	// 좌표 
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	// 어항 정보
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	
	// 상어 정보
	static int shark_size = 2;
	static int shark_dist = 0;
	static int shark_eating_size = 0;
	static int shark_x;
	static int shark_y;
	
	static int time = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if ( arr[i][j] == 9 ) {
					shark_x = i;
					shark_y = j;
					arr[i][j] = shark_size;
				}
			}
		}
		
		func();
		
		System.out.println(time);
	}
	
	public static void func() {
		
		while ( !isNoFish() ) {
			Fish f = findFish(shark_x, shark_y, shark_dist);
			
			if ( f == null )
				break;
			else
				move(f);
		}
		
	}
	
	public static Fish findFish(int x, int y, int dist) {
		
		Queue<int[]> q = new LinkedList<>(); // 갈 수 있는 좌표 정보
		PriorityQueue<Fish> pq = new PriorityQueue<>(); // 갈 수 있는 좌표에 있는 먹을 수 있는 물고기 정보
		
		q.add(new int[] {x, y, 0});
		
		while ( !q.isEmpty()) {
			
			int[] point = q.poll();
			
			x = point[0];
			y = point[1];
			dist = point[2];
			
			if ( visited[x][y] )
				continue;
			
			visited[x][y] = true;
			
			for(int i = 0; i < 4; i++) {
				
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if ( 0 > nextX || nextX > arr.length - 1 || 0 > nextY || nextY > arr[0].length - 1 )
					continue;
				
				if ( visited[nextX][nextY])
					continue;
				
				if ( arr[shark_x][shark_y] >= arr[nextX][nextY] ) { // 갈 수 있는 좌표면
					
					q.add(new int[] {nextX, nextY, dist + 1});
					
					if ( arr[nextX][nextY] != 0 && arr[nextX][nextY] < arr[shark_x][shark_y] ) { // 해당 좌표에 물고기가 있고, 그 물고기가 상어가 먹을 수 있는 물고기면
						pq.offer(new Fish(nextX, nextY, dist + 1));
						visited[nextX][nextY] = true;
					}
						
					
				}
			}
		}
		
		
		return pq.poll();
		
	}
	
	public static void move(Fish fish) {
		
		int x = fish.x;
		int y = fish.y;
		int dist = fish.dist;
		
		arr[shark_x][shark_y] = 0; // 이전 좌표는 0으로 처리

		shark_x = x; // 상어 좌표 조정
		shark_y = y; 
		
		shark_eating_size++; // 상어가 먹은 물고기수 증가
		
		if ( shark_size <= shark_eating_size ) { // 상어 크기가 2인데, 먹은 물고기 수가 3일 경우
			shark_eating_size = 0; // 먹은 물고기 0으로 초기화
			shark_size++; // 상어 크기 3으로 증가
		}
		
		arr[x][y] = shark_size; // 현재 좌표는 상어 크기로 처리
		
		time += dist; // 결과값 갱신
		
		visited = new boolean[visited.length][visited.length];
			
	}
	
	public static boolean isNoFish() {
		
		for(int i = 0; i < arr.length; i++ ) {
			for(int j = 0; j < arr[0].length; j++ ) {
				if ( arr[i][j] != 0 && arr[i][j] < arr[shark_x][shark_y] ) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
