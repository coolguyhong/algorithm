package dp.koitp.max_subarray_ring;

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
    private static int[] max;
    private static int[] min;
    private static int sum;

    // koitp.org
    // 최대구간합(환) : DP
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = new int[N+1];
            max = new int[N+1];
            min = new int[N+1];
            sum = 0;
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                sum += A[i];
            }

            int maxSum = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                max[i] = max(max[i-1], 0) + A[i];
                min[i] = min(min[i-1], 0) + A[i];
                maxSum = max(maxSum, max[i]);
            }

            int minSum = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                minSum = max(minSum, sum - min[i]);
            }

            int result = max(maxSum, minSum);

            bw.write(((result < 0) ? 0 : result) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
