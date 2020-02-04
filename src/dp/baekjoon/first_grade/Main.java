package dp.baekjoon.first_grade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[] n;
    private static long[][] D;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/5557
    // 1학년(5557): 조합론
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        n = new int[N];
        D = new long[N][21];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            n[i] = Integer.parseInt(st.nextToken());
        }

        D[0][n[0]] = 1;
        for (int i = 1; i < N-1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (D[i-1][j] == 0) {
                    continue;
                }

                if (j - n[i] >= 0) {
                    D[i][j-n[i]] += D[i-1][j];
                }
                if (j + n[i] <= 20) {
                    D[i][j+n[i]] += D[i-1][j];
                }
            }
        }

        bw.write(D[N-2][n[N-1]] + "\n");
        bw.close();
    }
}
