package dp.koitp.become_one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[] D;

    // koitp.org
    // become_one dp
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = new int[N+1];
        for (int i = 0; i <= N; i++) {
            D[i] = -1;
        }
        D[1] = 0;

        solve(N);
        bw.write(D[N] + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve(int n) {
        if (D[n] != -1) {
            return D[n];
        }

        int res = solve(n-1);
        if (n % 2 == 0) {
            res = min(res, D[n/2]);
        }
        if (n % 3 == 0) {
            res = min(res, D[n/3]);
        }

        return D[n] = res + 1;
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
