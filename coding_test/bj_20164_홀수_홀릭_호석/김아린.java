import java.util.*;
import java.io.*;

public class Main {
    static int Min = Integer.MAX_VALUE, Max = -1;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        solve(s, 0);
        System.out.println(Min + " " + Max);
    }

    public static void solve(String s, int cnt) {
        int sum = 0;
        if (s.length() == 1) {
            cnt += getOdd(Integer.parseInt(s));
            Min = Math.min(Min, cnt);
            Max = Math.max(Max, cnt);
            return;
        } else if (s.length() == 2) {
            cnt += getOdd(Integer.parseInt(s));
            int n = (s.charAt(0) - '0') + (s.charAt(1) - '0');        
            solve(Integer.toString(n), cnt);
        } else if (s.length() >= 3) {
            cnt += getOdd(Integer.parseInt(s));
            for (int i = 1; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    String x = s.substring(0, i);
                    String y = s.substring(i, j);
                    String z = s.substring(j);
                    int n = Integer.parseInt(x) + Integer.parseInt(y) + Integer.parseInt(z);
                    solve(Integer.toString(n), cnt);
                }
            }
        }
    }

    public static int getOdd(int x) {
        int ret = 0;
        while (x > 0) {
            if ((x % 10) % 2 == 1) {
                ret++;
            }
            x /= 10;
        }
        return ret;
    }
}
