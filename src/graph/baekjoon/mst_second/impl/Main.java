package graph.baekjoon.mst_second.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E, K, minCost, ans;
    private static int[] parents, depth, visited;
    private static List<int[]> edges; // edge는 간선정보
    private static List<int[]>[] trees; // trees는 연결된 간선의 links 정보
    private static int[] mstEdge; // mst가 되는 edge 학인하기 위한 배열 mst 배열에 1을 넣어줌
    private static int[][] par, maxCost;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/1626
    // 두 번째로 작은 스패닝 트리 크루스칼, lca
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int v = V;
        K = 0;
        while (v > 0) {
            v /= 2;
            K++;
        }

        trees = new ArrayList[V+1];
        mstEdge = new int[E];
        parents = new int[V+1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
            trees[i] = new ArrayList<>();
        }

        minCost = 0;

        // 간선 저장
        edges = new ArrayList<>();
        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edges.add(new int[]{i, a, b, c});
        }

        // 최소 스패닝 트리를 구하기 위한 크루스칼
        int ret = kruskal();
        if (ret == -1) {
            bw.write("-1\n");
        } else {
            secondMst();
            bw.write(ans + "\n");
        }
        bw.close();
    }

    private static int kruskal() {
        Collections.sort(edges, (e1, e2) -> Integer.compare(e1[3], e2[3]));

        int cnt = 0;
        for (int[] edge : edges) {
            int s = edge[1];
            int e = edge[2];

            int ps = find(s);
            int pe = find(e);
            if (ps == pe) {
                continue;
            }

            // union
            parents[pe] = ps;

            int idx = edge[0];
            int cost = edge[3];
            // tree 만들기 연결된 간선 정보로 그래프 만들기
            trees[s].add(new int[]{idx, e, cost});
            trees[e].add(new int[]{idx, s, cost});
            mstEdge[idx]++;
            minCost += cost;
            cnt++;
        }
        // mst로 연결이 되지 않을 경우 -1 return
        if (cnt != V-1) {
            return -1;
        }

        return 0;
    }

    private static int find(int s) {
        if (parents[s] == s) {
            return s;
        } else {
            return parents[s] = find(parents[s]);
        }
    }

    private static void secondMst() {
        visited = new int[V+1];
        depth = new int[V+1];
        par = new int[K][V+1];
        maxCost = new int[K][V+1];

        // bfs 돌려서 depth와 부모 저장
        bfs();
        fillParents();

        // tree 로 연결이 되지 않은 간선의 lca 구하기
        for (int[] edge : edges) {
            if (mstEdge[edge[0]] > 0) { // mst 연결된 것 패스
                continue;
            }

            int s = edge[1];
            int e = edge[2];
            int c = edge[3];

            int res = lca(s, e, c);

            if (minCost != 1 && c != 1 && res == 0) {
                continue;
            }

            ans = Math.min(ans, minCost - res + c);
        }

        if (ans == Integer.MAX_VALUE || ans == minCost) {
            ans = -1;
        }
    }

    private static int lca(int a, int b, int c) {
        int ret = 0;

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        int k = 0;
        while (diff > 0) {
            if (diff % 2 == 1) {
                if (maxCost[k][a] == c) {
                    ret = Math.max(ret, getMax(a, k, c));
                } else {
                    ret = Math.max(ret, maxCost[k][a]);
                }
                a = par[k][a];
            }
            diff /= 2;
            k++;
        }

        if (a == b) {
            return ret;
        }

        for (k = K-1; k >= 0; k--) {
            if (par[k][a] == par[k][b]) {
                continue;
            }
            if (maxCost[k][a] == c) {
                ret = Math.max(ret, getMax(a, k, c));
            } else {
                ret = Math.max(ret, maxCost[k][a]);
            }
            if (maxCost[k][b] == c) {
                ret = Math.max(ret, getMax(b, k, c));
            } else {
                ret = Math.max(ret, maxCost[k][b]);
            }

            a = par[k][a];
            b = par[k][b];
        }

        ret = Math.max(ret, (maxCost[0][a] == c) ? ret : maxCost[0][a]);
        ret = Math.max(ret, (maxCost[0][b] == c) ? ret : maxCost[0][b]);

        return ret;
    }

    private static int getMax(int a, int k, int c) {
        if (k == 0) {
            return 0;
        }

        int ret = -1;
        if (maxCost[k-1][a] == c) {
            ret = Math.max(ret, getMax(a, k-1, c));
        } else {
            ret = Math.max(ret, maxCost[k-1][a]);
        }

        if (maxCost[k-1][par[k-1][a]] == c) {
            ret = Math.max(ret, getMax(par[k-1][a], k-1, c));
        } else {
            ret = Math.max(ret, maxCost[k-1][par[k-1][a]]);
        }

        return ret;
    }

    private static void fillParents() {
        for (int k = 1; k < K; k++) {
            for (int i = 1; i <= V; i++) {
                par[k][i] = par[k-1][par[k-1][i]];
                maxCost[k][i] = Math.max(maxCost[k-1][i], maxCost[k-1][par[k-1][i]]);
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
            for (int[] c : trees[n]) {
                int next = c[1];
                int cost = c[2];
                if (par[0][n] == next) {
                    continue;
                }

                par[0][next] = n;
                maxCost[0][next] = cost;
                depth[next] = depth[n] + 1;
                q.add(next);
            }
        }

    }
}
