package graph.koitp.merchant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K;
    private static int[] depth;
    private static List<Integer>[] links;
    private static int[][] parents;

    // koitp.org
    // https://koitp.org/problem/MERCHANT/read/
    // merchant : lca
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int n = N;
        K = 0;
        while (n > 0) {
            n /= 2;
            K++;
        }

        links = new ArrayList[N+1];
        parents = new int[K][N+1];
        depth = new int[N+1];

        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            links[a].add(b);
            links[b].add(a);
        }

        makeGraph();
        fillParents();

        long ans = depth[2] - depth[1];
        for (int i = 3; i <= N; i++) {
            ans += lca(i-1, i);
        }

        bw.write(ans + "\n");
        bw.close();
    }

    private static long lca(int a, int b) {
        if (depth[b] > depth[a]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int originA = a;
        int originB = b;

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
            return depth[originA] - depth[originB];
        }

        for (k = K-1; k >=0 ; k--) {
            if (parents[k][a] == parents[k][b]) {
                continue;
            }

            a = parents[k][a];
            b = parents[k][b];
        }

        int lca = parents[0][a];

        long depth1 = depth[originA] - depth[lca];
        long depth2 = depth[originB] - depth[lca];

        return depth1 + depth2;
    }

    private static void fillParents() {
        for (int k = 1; k < K; k++) {
            for (int i = 1; i <= N; i++) {
                parents[k][i] = parents[k-1][parents[k-1][i]];
            }
        }
    }

    private static void makeGraph() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        depth[1] = 1;

        int n;
        while (!q.isEmpty()) {
            n = q.poll();
            for (int c : links[n]) {
                // 자기 부모면 가지 않는다.
                if (parents[0][n] == c) {
                    continue;
                }

                depth[c] = depth[n] + 1;
                parents[0][c] = n;
                q.add(c);
            }
        }
    }
}
