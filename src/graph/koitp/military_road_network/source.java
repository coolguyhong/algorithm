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
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        D = new int[N+1];
        for (int i = 1; i <= N; i++) {
            D[i] = i;
        }

        long ans = 0;
        int c;
        links = new Link[M+K];
        for (int i = 0; i < M+K; i++) {
            links[i] = new Link();
            st = new StringTokenizer(br.readLine());
            links[i].x = Integer.parseInt(st.nextToken());
            links[i].y = Integer.parseInt(st.nextToken());
            links[i].c = Integer.parseInt(st.nextToken());
            if (i < M) {
                ans += links[i].c;
                links[i].c = -links[i].c;
            }
        }

        // 간선 가중치 오름차순 정렬
        Arrays.sort(links, (a, b) -> Integer.compare(a.c, b.c));

        added = new boolean[M+K];
        can = new boolean[M+K];
        for (int i = 0, j = 0; i < M+K; i++) {
            for (; j < M+K && links[i].c == links[j].c; j++) {
                if (find(links[j].x) != find(links[j].y)) {
                    can[j] = true;
                }
            }

            int px = find(links[i].x);
            int py = find(links[i].y);
            if (px != py) {
                ans += links[i].c;
                added[i] = true;
                D[py] = px;
            }
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

    private static int find(int x) {
        if (D[x] == x) {
            return x;
        } else {
            return D[x] = find(D[x]);
        }
    }
}

class Link {
    int x;
    int y;
    int c;
}
