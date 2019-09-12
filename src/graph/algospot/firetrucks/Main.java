package graph.algospot.firetrucks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E, N, M;
    private static int[] dist;
    private static final int max_value = 500000001;
    private static List<int[]>[] links;
    private static int[] n;
    private static int[] m;

    // 알고스팟 문제 해결 전략
    // https://algospot.com/judge/problem/read/FIRETRUCKS
    // 소방차(FIRETRUCKS): 그래프, 최단거리, 다익스트라
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[V+1];
            n = new int[N+1];
            m = new int[M+1];
            links = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) {
                dist[i] = max_value;
                links[i] = new ArrayList<>();
            }

            int a, b, t;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());

                links[a].add(new int[]{b, t});
                links[b].add(new int[]{a, t});
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                n[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                m[i] = Integer.parseInt(st.nextToken());
            }

            dijkstra();
            int ans = 0;
            for (int i = 1; i <= N; i++) {
                ans += dist[n[i]];
            }

            bw.write(ans + "\n");
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for (int i = 1; i <= M; i++) {
            dist[m[i]] = 0;
            pq.add(new int[]{m[i], 0});
        }

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();
            if (dist[n[0]] < n[1]) {
                continue;
            }

            for (int[] k : links[n[0]]) {
                if (dist[n[0]] + k[1] < dist[k[0]]) {
                    dist[k[0]] = dist[n[0]] + k[1];
                    pq.add(new int[]{k[0], dist[k[0]]});
                }
            }
        }
    }
}
