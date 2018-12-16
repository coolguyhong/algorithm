package dp.koitp.lis;

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
    // LIS: DP
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
            D[i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j]) {
                    D[i] = max(D[i], D[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = max(max, D[i]);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();

    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}