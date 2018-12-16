package graph.baekjoon.alphabet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int R, C;
    private static char[][] A;
    private static int[] visited = new int[26];
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int depth, ans;

    // 백준 알고리즘
    // 알파벳(1987): 그래프, DFS
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        A = new char[R+1][C+1];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 1; j <= C; j++) {
                A[i][j] = str.charAt(j-1);
            }
        }

        ans = 1;
        dfs(1, 1);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();


    }

    private static void dfs(int r, int c) {
        visited[A[r][c] - 'A'] = 1;
        depth++;
        for (int k = 0; k < 4; k++) {
            int nr = r + dx[k];
            int nc = c + dy[k];

            if (nr > 0 && nr <= R && nc > 0 && nc <= C) {
                if (visited[A[nr][nc] - 'A'] != 0) {
                    continue;
                }

                dfs(nr, nc);
                ans = Math.max(depth, ans);
                depth--;
                visited[A[nr][nc] - 'A'] = 0;
            }
        }
    }
}
