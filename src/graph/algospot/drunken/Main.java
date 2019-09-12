package graph.algospot.drunken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E;
    private static int[] A;
    private static int[][] D;
    private static int[][] W;
    private static final int max_value = 1000000000;

    // 알고스팟 문제 해결 전략
    // https://algospot.com/judge/problem/read/DRUNKEN
    // 음주 운전 단속(DRUNKEN): 그래프, 플로이드 워셜 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        A = new int[V];
        W = new int[V][V];
        D = new int[V][V];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < V; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    continue;
                }

                D[i][j] = max_value;
            }
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());

            D[a][b] = c;
            D[b][a] = c;
        }

        floyd();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int s, e;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()) - 1;
            e = Integer.parseInt(st.nextToken()) - 1;

            bw.write(W[s][e] + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void floyd() {
        List<int[]> order = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            order.add(new int[]{i, A[i]});
        }
        Collections.sort(order, (int[] a, int[] b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    W[i][j] = 0;
                } else {
                    W[i][j] = D[i][j];
                }
            }
        }

        for (int k = 0; k < V; k++) {
            int[] o = order.get(k);
            int w = o[0];
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    D[i][j] = min(D[i][j], D[i][w] + D[w][j]);
                    W[i][j] = min(W[i][j], D[i][j] + A[w]);
                }
            }
        }
    }

    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
