package graph.koitp.military_road_network;

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

    private static int N, M, K;
    private static int[] D;
    private static Link[] links;
    private static boolean[] added, can;
    private static final String unique = "unique";
    private static final String not = "not unique";

    // sw 문제풀이반
    // https://koitp.org/problem/MILITARY_ROAD_NETWORK/read/
    // 군사도로망: union-find & kruskal 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = new int[N+1];
        for (int i = 1; i <= N; i++) {
            D[i] = i;
        }

        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        links = new Link[M+K];
        added = new boolean[M+K];
        can = new boolean[M+K];
        int s, e, c;
        long ans = 0;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            links[i] = new Link(s, e, c);
            if (i < M) {
                ans += c;
                links[i].c = -links[i].c;
            }
        }

        Arrays.sort(links, (a, b) -> Integer.compare(a.c, b.c));
        int cnt = 0;
        for (int i = 0, j = 0; i < M+K; i++) {
            for (;j < M+K && links[i].c == links[j].c; j++) {
                if (find(links[j].s) != find(links[j].e)) {
                    can[j] = true;
                }
            }

            int ps = find(links[i].s);
            int pe = find(links[i].e);

            if (ps == pe) {
                continue;
            }

            if (cnt == N-1) {
                break;
            }

            cnt++;
            ans += links[i].c;
            D[pe] = ps;
            added[i] = true;
        }

        String ans2 = unique;
        for (int i = 0; i < M; i++) {
            if (!added[i] && can[i]) {
                ans2 = not;
                break;
            }
        }

        bw.write(ans + " " + ans2);
        bw.close();
    }

    private static int find(int s) {
        if (D[s] == s) {
            return s;
        } else {
            return D[s] = find(D[s]);
        }
    }

}

class Link {
    int s, e, c;

    Link(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }
}
