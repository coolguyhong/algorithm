package graph.koitp.build_expressway;

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

    private static int N, M;
    private static int[][] path;
    private static int[] D;

    // koitp.org
    // https://koitp.org/problem/BUILD_EXPRESSWAY/read/
    // 고속도로 건설(최소 신장 트리): 크루스칼 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = new int[N+1];
        for (int i = 1; i <= N; i++) {
            D[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        path = new int[M][3];
        int s, e, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            path[i][0] = s;
            path[i][1] = e;
            path[i][2] = c;
        }

        Arrays.sort(path, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        long ans = 0;
        int link = 0;
        int a, b;
        for (int i = 0; i < M; i++) {
            a = path[i][0];
            b = path[i][1];
            if (isUnion(a, b)) {
                continue;
            } else {
                union(a, b);
                link++;
                ans += path[i][2];
            }

            if (link == N-1) {
                break;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            D[pb] = pa;
        }
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }

    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
}
