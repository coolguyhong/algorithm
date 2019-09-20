package graph.baekjoon.monkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, Q;
    private static int [][] D, monkey, ans;
    private static int[] time;
    private static final int INF = 100000001;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/1602
    // 도망자 원숭이, 플로이드
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        D = new int[N+1][N+1];
        ans = new int[N+1][N+1];
        monkey = new int[N+1][2];
        time = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            monkey[i][0] = i;
            monkey[i][1] = Integer.parseInt(st.nextToken());
            time[i] = monkey[i][1];
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                D[i][j] = INF;
                ans[i][j] = INF;
            }
        }

        int a, b, d;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            D[a][b] = d;
            D[b][a] = d;
        }

        Arrays.sort(monkey, 1, N+1, (e, f) -> Integer.compare(e[1], f[1]));
        floyd();

        int s, t;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            if (ans[s][t] >= INF) {
                bw.write("-1\n");
            } else {
                bw.write(ans[s][t] + "\n");
            }
        }
        bw.close();
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            int p = monkey[k][0];
            int w = monkey[k][1];
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    D[s][e] = Math.min(D[s][p] + D[p][e], D[s][e]);
                    ans[s][e] = Math.min(ans[s][e], D[s][e] + Math.max(Math.max(time[s], time[e]), w));
                }
            }
        }
    }
}
