import java.io.*;
import java.util.*;

public class Main {
	
	public static int[][] arr;
	public static boolean[][] visitedOfAir;
	public static boolean[][] taskCompleted;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int year = 0;
	static int preCount = 0;
	static int count = 0;
	
	static boolean isAllMelted = false;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[M][N];
		visitedOfAir = new boolean[M][N];
		taskCompleted = new boolean[M][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if ( arr[i][j] == 1 ) {
					count++;
				}
			}
		}
		
		findAir(0, 0);
		
		for(int i = 0; i < visitedOfAir.length; i++ ) {
			Arrays.fill(visitedOfAir[i], false);			
		}
		
		while ( count != 0 ) {
			preCount = count;
			count = 0;
			func();
			year++;
			findAir(0, 0);
			
			for(int i = 0; i < visitedOfAir.length; i++ ) {
				Arrays.fill(visitedOfAir[i], false);			
			}
		}
		
		System.out.println(year);
		System.out.println(preCount);
	}
	
	public static void findAir(int x, int y) { // 공기 칸: -1, 빈 칸 : 0
		
		if ( visitedOfAir[x][y] )
			return;
		
		visitedOfAir[x][y] = true;
		arr[x][y] = -1;
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if ( nextX < 0 || nextX > arr.length - 1 || nextY < 0 || nextY > arr[0].length - 1 || arr[nextX][nextY] == 1 ) {
				continue;
			}
			
			findAir(nextX, nextY);
		}
		
		return;
	}
	
	public static void func() { // 동 서 남 북
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				
				if ( arr[i][j] == 1 ) { // 치즈 칸이면
					
					for(int k = 0; k < 4; k++) { // 동서남북 확인
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						
						if ( nextX < 0 || nextX > arr.length - 1  || nextY < 0 || nextY > arr[0].length - 1 )
							continue;
						
						if ( arr[nextX][nextY] == -1 && !taskCompleted[nextX][nextY]) { // 주변 칸 중 공기 칸이 있고, 그 칸이 아직 작업 처리를 하지 않은 칸이라면
							arr[i][j] = -1; // 해당 칸도 공기 칸으로 처리
							taskCompleted[i][j] = true;
							break;
						}
					}
				}
				
				if ( arr[i][j] == 1 ) // 앞의 작업을 수행하고도 살아남은 치즈 개수 count
					count++;
			}
		}
		
		for(int i = 0; i < taskCompleted.length; i++ ) {
			Arrays.fill(taskCompleted[i], false);			
		}

	}

}
