package dp.baekjoon.big_square;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] S;
    private static int[][] dp;

    // 백준 알고리즘
    // 가장 큰 정사각형 1915 termsum
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        S = new int[N+1][M+1];
        dp = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            String tmp = br.readLine();
            for (int j = 1; j <= M; j++) {
                S[i][j] = tmp.charAt(j-1) - '0';
            }
        }

        int ans = 0;
        // dp배열은 i, j를 오른쪽 아래 모서리를 하는 가장 큰 정사각형 한 변의 길이
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (S[i][j] == 1) {
                    dp[i][j] = min(dp[i-1][j], min(dp[i-1][j-1], dp[i][j-1])) + 1;
                    ans = max(ans, dp[i][j]);
                }
            }
        }

        bw.write(ans * ans + "\n");
        bw.flush();
        bw.close();
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
