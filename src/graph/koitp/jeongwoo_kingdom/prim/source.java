package graph.koitp.jeongwoo_kingdom.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static Node[] nodes;
    private static long[][] G;
    private static boolean[] added;
    private static int[] parent;
    private static long[] minLength;
    private static final long max = 200000001;

    // sw 문제풀이반
    // https://koitp.org/problem/SDS_3_5/read/
    // 정우 왕국, prim(memory out)
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1];
        parent = new int[N+1];
        added = new boolean[N+1];
        minLength = new long[N+1];
        int x, y, z;
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
            minLength[i] = max;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            nodes[i].x = x;
            nodes[i].y = y;
            nodes[i].z = z;
        }

        // 그래프 가중치 계산
        G = new long[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                long X = Math.abs(nodes[i].x - nodes[j].x);
                long Y = Math.abs(nodes[i].y - nodes[j].y);
                long Z = Math.abs(nodes[i].z - nodes[j].z);

                long c = Math.min(X, Math.min(Y, Z));
                G[i][j] = c;
                G[j][i] = c;
            }
        }

        bw.write(prim() + "\n");
        bw.close();
    }

    private static long prim() {
        long ans = 0;

        minLength[1] = 0;
        parent[1] = 1;

        for (int i = 1; i <= N; i++) {
            int u = -1;
            for (int v = 1; v <= N; v++) {
                if (!added[v]&& (u == -1 || minLength[u] > minLength[v])) {
                    u = v;
                }
            }

            ans += minLength[u];
            added[u] = true;

            for (int j = 1; j <= N; j++) {
                if (!added[j] && minLength[j] > G[u][j]) {
                    parent[j] = u;
                    minLength[j] = G[u][j];
                }
            }
        }

        return ans;
    }
}

class Node {
    int x, y, z;
}