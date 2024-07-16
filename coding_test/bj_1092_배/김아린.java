import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] crane;
    static ArrayList<Integer> box;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        crane = new int[N];
        box = new ArrayList<>();

        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
                
        Arrays.sort(crane);
        Collections.sort(box, Collections.reverseOrder());
        
        if(crane[N-1] < box.get(0)) {
            System.out.println(-1);
            return;
        }
        
        int cnt = 0;
        while(!box.isEmpty()) {            
            int idx = 0;
            
            for (int i = N-1; i >= 0; ) {
                if(idx == box.size()) break;
                if(crane[i] >= box.get(idx)) {
                    box.remove(idx);
                    i--;
                } else {
                    idx++;
                }
            }
            cnt++;
            
        }
        System.out.println(cnt);
        
    }
}  
