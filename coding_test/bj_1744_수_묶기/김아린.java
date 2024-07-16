import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        int minus = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] <= 0) {
                minus++;
            }
        }
        Arrays.sort(arr);

        int ans = 0;
        // 음수 부분(0포함) - 두 개씩 묶어서 더하기
        for (int i = 1; i < minus; i += 2) {
            ans += (arr[i - 1] * arr[i]);
        }

        // 음수(0포함)가 홀수 개일 경우 그냥 더하기
        if (minus % 2 == 1) {
            ans += arr[minus - 1];
        }

        // 양수가 홀수 개일 경우 그냥 더하기
        if ((N - minus) % 2 == 1) {
            ans += arr[minus];
        }

        // 양수 부분 - 두 개씩 묶어서 더하기
        for (int i = N - 1; i > minus ; i -= 2) {
            int mul = arr[i] * arr[i - 1];
            int sum = arr[i] + arr[i - 1];
            if (mul > sum) {
                ans += mul;
            } else {
                ans += sum;
            }
        }
        
        System.out.println(ans);
    }
}
