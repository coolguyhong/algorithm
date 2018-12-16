package etc.koitp.tax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K;
    private static long[] A;
    private static long[] sum;
    private static long[] ans;

    // koitp.org
    // tax : 구간 합 구하기 응용인듯, 기타 유형
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new long[N+1];
        sum = new long[K+1];
        ans = new long[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 1; i <= N ; i++) {
            if (i < K) {
                for (int k = 0; k < i; k++) {
                    sum[k] = sum[k]+ max(A[i], 0);
                }
                sum[i] = A[i];
            } else {
                for (int k = 0; k < K; k++) {
                    sum[k] = sum[k]+ A[i];
                }
                sum[K] = A[i];
            }

            long temp;
            for (int k = 0; k < K-1; k++) {
                if (sum[k+1] > sum[k]) {
                    temp = sum[k];
                    sum[k] = sum[K+1];
                    sum[k+1] = temp;
                }
            }
            sum[K] = Long.MIN_VALUE;
        }

    }

    private static long max(long a, long b) {
        return (a > b) ? a : b;
    }
}
