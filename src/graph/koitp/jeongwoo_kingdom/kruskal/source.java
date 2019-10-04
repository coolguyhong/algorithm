package graph.koitp.jeongwoo_kingdom.kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static long[] X, Y, Z;
    private static int[] D;
    private static List<Edge> edges;

    // sw 문제풀이반
    // https://koitp.org/problem/SDS_3_5/read/
    // 정우 왕국, kruskal(memory out)
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        X = new long[N+1];
        Y = new long[N+1];
        Z = new long[N+1];
        D = new int[N+1];
        int x, y, z;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            X[i] = x;
            Y[i] = y;
            Z[i] = z;
            D[i] = i;
        }

        // 그래프 가중치 계산
        edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                long dx = abs(X[i]-X[j]);
                long dy = abs(Y[i]-Y[j]);
                long dz = abs(Z[i]-Z[j]);

                long c = min(dx, min(dy, dz));
                edges.add(new Edge(i, j, c));
            }
        }

        bw.write(kruskal() + "\n");
        bw.close();
    }

    private static long kruskal() {
        Collections.sort(edges, (a, b) -> Long.compare(a.c, b.c));

        long ans = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            int ps = find(edge.s);
            int pe = find(edge.e);
            if (ps == pe) {
                continue;
            }

            if (cnt == N-1) {
                break;
            }

            cnt++;
            D[pe] = ps;
            ans += edge.c;
        }

        return ans;
    }

    private static int find(int s) {
        if (D[s] == s) {
            return s;
        } else {
            return D[s] = find(D[s]);
        }
    }


    private static long min(long a, long b) {
        return a < b ? a : b;
    }

    private static long abs(long a) {
        return a < 0 ? -a : a;
    }
}

class Edge {
    int s, e;
    long c;

    Edge(int s, int e, long c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }
}
