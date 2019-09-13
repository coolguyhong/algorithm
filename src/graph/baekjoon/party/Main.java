package graph.baekjoon.party;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, X;
    private static int[] F, B;
    private static List<int[]>[] links, re_links;
    private static final int max = 1000001;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        F = new int[N+1];
        B = new int[N+1];
        links = new ArrayList[N+1];
        re_links = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            F[i] = max;
            B[i] = max;
            links[i] = new ArrayList<>();
            re_links[i] = new ArrayList<>();
        }

        int a, b, t;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            links[a].add(new int[]{b, t});
            re_links[b].add(new int[]{a, t});
        }

        go_dijkstra();
        back_dijkstra();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (ans < F[i] + B[i]) {
                ans = F[i] + B[i];
            }
        }

        bw.write(ans + "\n");
        bw.close();
    }

    private static void back_dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{X, 0});
        B[X] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (B[n[0]] < n[1]) {
                continue;
            }
            for (int[] link : links[n[0]]) {
                if (B[link[0]] > B[n[0]] + link[1]) {
                    B[link[0]] = B[n[0]] + link[1];
                    pq.add(new int[]{link[0], B[link[0]]});
                }
            }
        }
    }

    private static void go_dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{X, 0});
        F[X] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (F[n[0]] < n[1]) {
                continue;
            }
            for (int[] link : re_links[n[0]]) {
                if (F[link[0]] > F[n[0]] + link[1]) {
                    F[link[0]] = F[n[0]] + link[1];
                    pq.add(new int[]{link[0], F[link[0]]});
                }
            }
        }
    }
}