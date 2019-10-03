package graph.koitp.mst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] G;
    private static int[] D;

    // sw 문제풀이반
    // https://koitp.org/problem/SDS_3_3/read/
    // 3강 3번 최소 신장 트리
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        D = new int[N+1];
        for (int i = 1; i <= N; i++) {
            D[i] = i;
        }

        M = Integer.parseInt(br.readLine());
        G = new int[M][3];
        int s, e, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            G[i][0] = s;
            G[i][1] = e;
            G[i][2] = c;
        }

        Arrays.sort(G, (a, b) -> Integer.compare(a[2], b[2]));

        int cnt = 0;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            s = G[i][0];
            e = G[i][1];
            c = G[i][2];

            if (isUnion(s, e)) {
                continue;
            }

            if (cnt == N-1) {
                break;
            }

            union(s, e);
            cnt++;
            ans += c;
        }

        bw.write(ans + "\n");
        bw.close();
    }

    private static void union(int s, int e) {
        int ps = find(s);
        int pe = find(e);
        if (ps != pe) {
            D[pe] = ps;
        }
    }

    private static boolean isUnion(int s, int e) {
        return find(s) == find(e);
    }

    private static int find(int s) {
        if (D[s] == s) {
            return s;
        } else {
            return D[s] = find(D[s]);
        }
    }
}
