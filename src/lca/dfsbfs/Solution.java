package lca.dfsbfs;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, Q, K;
    private static int[][] parents;
    private static int[] depth;
    private static List<Integer>[] G;

    public static void main(String[] args) throws Exception {
        InputReader input = new InputReader(System.in);
//        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            Q = input.nextInt();

            G = new List[N+1];
            for (int i = 1; i <= N; i++) {
                G[i] = new ArrayList<>();
            }

            K = 0;
            int n = N;
            while (n > 0) {
                K++;
                n /= 2;
            }
            
            parents = new int[K][N+1];
            depth = new int[N+1];
            
            int p;
            for (int i = 2; i <= N; i++) {
                p = input.nextInt();
                G[i].add(p);
                G[p].add(i);
            }
            
            dfs(1, 0);
//            bfs();
            fillParents();

            int a, b;
            bw.write("#" + testCase + " ");
            while (Q-- > 0) {
                a = input.nextInt();
                b = input.nextInt();

                bw.write(lca(a, b) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int lca(int a, int b) {
        // a의 depth를 더 큰 수로 지정
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
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
                // 부모 관계일 경우
                if (c == parents[0][n]) {
                    continue;
                }

                depth[c] = depth[n] + 1;
                parents[0][c] = n;
                q.add(c);
            }
        }
    }

    private static void dfs(int v, int p) {
        // 방문했으면 return
        if (depth[v] > 0) {
            return;
        }

        // depth update
        depth[v] = depth[p] + 1;
        parents[0][v] = p;
        for (int c : G[v]) {
            // 간선이 부모일 경우 pass
            if (c == p) {
                continue;
            }
            dfs(c, v);
        }
    }
}

class InputReader  {
    private BufferedReader br;
    private StringTokenizer st;

    public InputReader(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream), 32768);
        st = null;
    }

    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}
