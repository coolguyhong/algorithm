package dp.koitp.flower;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int F, V;
    private static int[][] A;
    private static int[][] D;

    // koitp.org
    // Flower 꽃
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        A = new int[F+1][V+1];
        D = new int[F+1][V+1];

        for (int i = 1; i <= F; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= V; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i <= F; i++) {
            for (int j = 0; j <= V; j++) {
                if (i == 0) {
                    D[i][j] = 0;
                    continue;
                }
                if (i > j) {
                    D[i][j] = Integer.MIN_VALUE;
                    continue;
                }
                D[i][j] = max(D[i-1][j-1] + A[i][j], D[i][j-1]);
            }
        }

        bw.write(D[F][V] + "\n");
        bw.flush();
        bw.close();
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
