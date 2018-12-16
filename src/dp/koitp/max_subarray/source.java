package dp.koitp.max_subarray;

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
    private static int[] A;
    private static int[] D;

    // koitp.org
    // 최대 구간 합 DP
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        D = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        long psum = Long.MIN_VALUE;
        long result = Long.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            psum = max(psum, 0) + A[i];
            result = max(result, psum);
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static long max(long a, long b) {
        return (a > b) ? a : b;
    }
}
