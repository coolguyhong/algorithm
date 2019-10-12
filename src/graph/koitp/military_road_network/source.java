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
    private static int[] par;
    private static boolean[] can, added;
    private static Link[] links;


    // sw 문제풀이반
    // https://koitp.org/problem/MILITARY_ROAD_NETWORK/read/
    // 군사도로망: union-find & kruskal 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        par = new int[N+1];
        for (int i = 1; i <= N; i++) {
            par[i] = i;
        }

        can = new boolean[M+K];
        added = new boolean[M+K];
        links = new Link[M+K];
        int a, b, c;
        long ans = 0;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            links[i] = new Link(a, b, c);
            if (i < M) {
                ans += c;
                links[i].c = -links[i].c;
            }
        }

        Arrays.sort(links);

        int cnt = 0;
        for (int i = 0, j = 0; i < M+K; i++) {
            for (; j < M+K && links[i].c == links[j].c; j++) {
                if (find(links[j].s) != find(links[j].e)) {
                    can[j] = true;
                }
            }

            int ps = find(links[i].s);
            int pe = find(links[i].e);

            if (ps == pe) {
                continue;
            }

            added[i] = true;
            par[pe] = ps;
            ans += links[i].c;
            cnt++;

            if (cnt == N-1) {
                break;
            }
        }

        String ans2 = "unique";
        for (int i = 0; i < M+K; i++) {
            if (!added[i] && can[i]) {
                ans2 = "not unique";
                break;
            }
        }

        bw.write(ans + " " + ans2 + "\n");
        bw.close();
    }

    private static int find(int s) {
        if (par[s] == s) {
            return s;
        } else {
            return par[s] = find(par[s]);
        }
    }
}

class Link implements Comparable<Link> {
    int s, e, c;

    Link(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }

    @Override
    public int compareTo(Link o) {
        return Integer.compare(this.c, o.c);
    }
}