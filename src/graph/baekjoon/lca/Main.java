package graph.baekjoon.lca;

import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, K;
    private static int[] depth;
    private static int[][] parents;
    private static List<Integer>[] G;

    // 백준 알고리즘
    // LCA 2(11438 번) : graph.samsung.lca 구하기
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        depth = new int[N+1];
        G = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }

        int n = N;
        K = 0;
        while (n > 0) {
            K++;
            n /= 2;
        }

        parents = new int[K][N+1];
        int a, b;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            G[a].add(b);
            G[b].add(a);
        }

        bfs();
        fillParents();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write(lca(a, b) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        int k = 0;
        while (diff > 0) {
            if (diff % 2 == 1) {
                a = parents[k][a];
            }
            k++;
            diff /= 2;
        }

        if (a == b) {
            return a;
        }

        for (k = K-1; k >= 0; k--) {
            if (parents[k][a] == parents[k][b]) {
                continue;
            }
            a = parents[k][a];
            b = parents[k][b];
        }
        return parents[0][a];
    }

    private static void fillParents() {
        for (int k = 1; k < K; k++) {
            for (int i = 1; i <= N; i++) {
                parents[k][i] = parents[k-1][parents[k-1][i]];
            }
        }
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        depth[1] = 1;

        int n;
        while (!q.isEmpty()) {
            n = q.poll();
            for (int c : G[n]) {
                if (c == parents[0][n]) {
                    continue;
                }

                depth[c] = depth[n] + 1;
                parents[0][c] = n;
                q.add(c);
            }
        }
    }
}
