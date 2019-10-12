package graph.baekjoon.mst_second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E, H, minCost, ans;
    private static int[] parents, visited, depth;
    private static List<int[]> edges;
    private static int[] mstEdge; // mst 엣지가 되는 엣지 번호에 cost 저장
    private static List<int[]>[] trees;
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
        H = 0;
        while (v > 0) {
            v /= 2;
            H++;
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
            // tree 만들기
            trees[s].add(new int[]{idx, e, cost});
            trees[e].add(new int[]{idx, s, cost});
            mstEdge[idx] = 1;
            minCost += cost;
            cnt++;
        }

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
        par = new int[V+1][H];
        maxCost = new int[V+1][H];

        // dfs 돌려서 depth 와 부모 저장
        dfs(1, 0);

        ans = Integer.MAX_VALUE;

        // tree 로 연결이 되지 않은 간선의 lca 구하기
        for (int[] edge : edges) {
            if (mstEdge[edge[0]] > 0) { // mst 연결된 간선 패스
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
        int h = 0;
        while (diff > 0) {
            if (diff % 2 == 1) {
                if (maxCost[a][h] == c) {
                    ret = Math.max(ret, getMax(a, h, c));
                } else {
                    ret = Math.max(ret, maxCost[a][h]);
                }
                a = par[a][h];
            }
            diff /= 2;
            h++;
        }

        if (a == b) {
            return ret;
        }

        for (h = H-1; h >= 0; h--) {
            if (par[a][h] == par[b][h]) {
                continue;
            }
            if (maxCost[a][h] == c) {
                ret = Math.max(ret, getMax(a, h, c));
            } else {
                ret = Math.max(ret, maxCost[a][h]);
            }
            if (maxCost[b][h] == c) {
                ret = Math.max(ret, getMax(b, h, c));
            } else {
                ret = Math.max(ret, maxCost[b][h]);
            }

            a = par[a][h];
            b = par[b][h];
        }

        ret = Math.max(ret, (maxCost[a][0] == c) ? ret : maxCost[a][0]);
        ret = Math.max(ret, (maxCost[b][0] == c) ? ret : maxCost[b][0]);

        return ret;
    }

    private static int getMax(int a, int k, int c) {
        if (k == 0) {
            return 0;
        }

        int ret = -1;
        if (maxCost[a][k-1] == c) {
            ret = Math.max(ret, getMax(a, k-1, c));
        } else {
            ret = Math.max(ret, maxCost[a][k-1]);
        }

        if (maxCost[par[a][k-1]][k-1] == c) {
            ret = Math.max(ret, getMax(par[a][k-1], k-1, c));
        } else {
            ret = Math.max(ret, maxCost[par[a][k-1]][k-1]);
        }

        return ret;
    }

    private static void dfs(int n, int p) {
        if (visited[n] != 0) {
            return;
        }

        visited[n]++;
        for (int[] node : trees[n]) {
            int next = node[1];
            int cost = node[2];

            if (visited[next] != 0) {
                continue;
            }

            depth[next] = depth[n] + 1;
            par[next][0] = n;
            maxCost[next][0] = cost;

            for (int i = 1; i < H; i++) {
                par[next][i] = par[par[next][i-1]][i-1];
                maxCost[next][i] = Math.max(maxCost[next][i-1], maxCost[par[next][i-1]][i-1]);
            }
            dfs(next, n);
        }
    }
}