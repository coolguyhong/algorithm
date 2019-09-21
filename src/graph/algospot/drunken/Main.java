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
    private static int[][] D, W;
    private static final int max_value = 12500001;

    // 알고스팟 문제 해결 전략
    // https://algospot.com/judge/problem/read/DRUNKEN
    // 음주 운전 단속(DRUNKEN): 그래프, 플로이드 워셜 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        A = new int[V+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        D = new int[V+1][V+1];
        W = new int[V+1][V+1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                D[i][j] = max_value;
                W[i][j] = max_value;
            }
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            D[a][b] = c;
            D[b][a] = c;
            W[a][b] = c;
            W[b][a] = c;
        }

        List<int[]> order = new ArrayList<>();
        order.add(new int[]{0, 0});
        for (int i = 1; i <= V; i++) {
            order.add(new int[]{i, A[i]});
        }
        Collections.sort(order, (a1, b1) -> Integer.compare(a1[1], b1[1]));

        // floyd 수행
        for (int k = 1; k <= V; k++) {
            int w = order.get(k)[0];
            for (int s = 1; s <= V; s++) {
                if (D[w][s] == max_value || D[w][s] == 0) {
                    continue;
                }
                for (int e = 1; e <= V; e++) {
                    D[s][e] = Math.min(D[s][e], D[s][w] + D[w][e]);
                    W[s][e] = Math.min(W[s][e], D[s][e] + A[w]);
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write(W[a][b] + "\n");
        }
        bw.close();
    }

}
