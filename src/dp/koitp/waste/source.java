package dp.koitp.waste;

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
    private static int[][] A;
    private static int[][] D;

    // koitp.org
    // 폐지줍기 dp
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        D = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                D[i][j] = max(D[i][j-1], D[i-1][j]) + A[i][j];
            }
        }

        bw.write(D[N][N] + "\n");
        bw.flush();
        bw.close();
    }
    
    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
