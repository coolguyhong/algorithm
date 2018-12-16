package graph.koitp.military_road_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, K;
    private static int[] D;
    private static int[][] G;
    private static long ans;
    private static String unique;

    // koitp.org
    // 군사도로망: union-find & kruskal 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        D = new int[N+1];
        for (int i = 1; i <= N; i++) {
            D[i] = i;
        }

        G = new int[M+K][3];
        int a, b, c;
        ans = 0;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            G[i][0] = a;
            G[i][1] = b;
            if (i < M) {
                G[i][2] = -c;
                ans += c;
            } else {
                G[i][2] = c;
            }
        }

        unique = "unique";
        kruskal();

        bw.write(ans + " " + unique + "\n");
        bw.flush();
        bw.close();
    }

    private static void kruskal() {
        Arrays.sort(G, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = Integer.compare(o1[2], o2[2]);
                if (compare == 0) {
                    compare = Integer.compare(o1[0], o2[0]);
                }
                if (compare == 0) {
                    unique = "not unique";
                }
                return compare;
            }
        });

        int a, b;
        for (int i = 0; i < M+K; i++) {
            a = G[i][0];
            b = G[i][1];

            if (isUnion(a, b)) {
                continue;
            } else {
                union(a, b);
                ans += G[i][2];
            }
        }
    }

    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        D[pa] = pb;
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }
}
