package greedy.baekjoon.money;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K;
    private static int[] money;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        money = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            money[i] = Integer.parseInt(st.nextToken());
        }

        int m;
        int idx = N-1;
        int ans = 0;
        while (K > 0) {
            int n = K / money[idx];
            if (n > 0) {
                m = n * money[idx];
                ans += n;
                K -= m;
            }
            idx--;
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}
