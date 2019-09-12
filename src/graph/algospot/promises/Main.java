package graph.algospot.promises;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int V, M, N;
    private static int[][] D;
    private static final int max_value = Integer.MAX_VALUE;
    private static int ans;

    // 알고스팟 문제 해결 능력
    // https://algospot.com/judge/problem/read/PROMISES
    // 선거 공약(PROMISES): 그래프, 플로이드
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            D = new int[V][V];
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (i == j) {
                        D[i][j] = 0;
                    } else {
                        D[i][j] = max_value;
                    }
                }
            }

            int a, b, c;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                D[a][b] = c;
                D[b][a] = c;
            }

            floyd();

            ans = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                update(a, b, c);
            }

            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void update(int a, int b, int c) {
        if (D[a][b] <= c) {
            ans++;
            return;
        }

        for (int s = 0; s < V; s++) {
            for (int e = 0; e < V; e++) {
                D[s][e] = min(D[s][e],
                        min(D[s][a] + c + D[b][e], D[s][b] + c + D[a][e]));
            }
        }
    }

    private static void floyd() {
        for (int k = 0; k < V; k++) {
            for (int s = 0; s < V; s++) {
                for (int e = 0; e < V; e++) {
                    if (D[s][e] > D[s][k] + D[k][e]) {
                        D[s][e] = D[s][k] + D[k][e];
                    }
                }
            }
        }
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
