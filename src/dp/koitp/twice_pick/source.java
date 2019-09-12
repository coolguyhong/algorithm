package dp.koitp.twice_pick;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] A;
    private static int[][] D;
    private static int[][] visited;

    // koitp.org
    // https://koitp.org/problem/TWICE_PICK/read/
    // 폐지 두번 줍기: DP
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[M+1][N+1];
            D = new int[M+1][N+1];
            visited = new int[M+1][N+1];

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                for (int j = 1; j <= N; j++) {
                    char c = str.charAt(j-1);
                    if (c == '#') {
                        A[i][j] = -1;
                    } else if (c == '*') {
                        A[i][j] = 1;
                    } else {
                        A[i][j] = 0;
                    }
                }
            }

            // 내려가는 DP
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    if (A[i][j] == -1) {
                        D[i][j] = -1;
                        continue;
                    }

                    if (D[i-1][j] >= D[i][j-1]) {
                        // 위에서 옴
                        visited[i][j] = 1;
                        D[i][j] = A[i][j] + D[i-1][j];
                    } else {
                        // 왼쪽에서 옴
                        visited[i][j] = 2;
                        D[i][j] = A[i][j] + D[i][j-1];
                    }
                }
            }

            int max_down = D[M][N];

            // 올라가는 DP
            D = new int[M+1][N+1];
            for (int i = M; i >= 1; i--) {

                for (int j = N; j >= 1; j--) {
                    if (A[i][j] == -1) {
                        D[i][j] = -1;
                        continue;
                    }

                    if (i == M) {
                        if (j+1 > N) {
                            D[i][j] = 0;
                        } else {
                            if (visited[i][j] != 0) {
                                A[i][j] = 0;
                            }
                            D[i][j] = D[i][j+1] + A[i][j];
                        }
                    } else {
                        if (j+1 > N) {
                            if (visited[i][j] != 0) {
                                A[i][j] = 0;
                            }
                            D[i][j] = A[i][j] + D[i+1][j];
                        } else {
                            if (visited[i][j] != 0) {
                                A[i][j] = 0;
                            }
                            D[i][j] = A[i][j] + max(D[i+1][j], D[i][j+1]);
                        }
                    }
                }
            }

            int max_up = D[1][1];
            bw.write(max_down + max_up + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
