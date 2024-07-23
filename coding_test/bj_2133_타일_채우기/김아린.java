import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int wall[] = new int[N+1];

        wall[0] = 1;
        for(int i = 2; i <= N; i += 2){
            wall[i] = wall[i-2] * 3;
            for(int j = i-4; j >= 0; j -= 2){
                wall[i] += wall[j] * 2;
            }
        }
        
        System.out.println(wall[N]);
    }
}
