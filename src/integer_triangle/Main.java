package integer_triangle;

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
    private static int[][] triangle;
    private static int[][] sum;

    // 백준 알고리즘
    // 정수 삼각형: 1932 DP
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        triangle = new int[N+1][N+1];
        sum = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i ; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeSum();

        int ans = 0;
        for (int i = 1; i <= N ; i++) {
            ans = max(ans, sum[N][i]);
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }

    private static void makeSum() {
        sum[0][0] = sum[0][1] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                sum[i][j] = triangle[i][j] + max(sum[i-1][j-1], sum[i-1][j]);
            }
        }
    }

    private static int max(int a, int b) {
        return (a >= b ? a : b);
    }
}
