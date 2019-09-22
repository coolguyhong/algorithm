package graph.baekjoon.commander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E, source, sink;
    private static List<Integer>[] links;
    private static int[][] capacity, flow, cost;
    private static int[] prev, dist;
    private static boolean[] contains;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/3640
    // 제독, 네트워크 유량, 최단거리, MCMF, 포드-풀커슨 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            st = new StringTokenizer(line);
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // u에서 v로 보낼 수 있는 용량
            capacity = new int[V*2 + 1][V*2 + 1];
            // u에서 v로 흘러가는 유량
            flow = new int[V*2 + 1][V*2 + 1];
            cost = new int[V*2 + 1][V*2 + 1];
            dist = new int[V*2 + 1];
            links = new ArrayList[V*2 + 1];
            for (int i = 1; i <= V*2; i++) {
                links[i] = new ArrayList<>();
            }

            // 가상 vertex를 만듬(평행하게 만들지 않기 위해)
            for (int i = 1; i <= V; i++) {
                links[i].add(i + V);
                links[i + V].add(i);
                capacity[i][i + V] = 1;
            }

            source = 1; // 시작점
            sink = V + V; // 도착점
            capacity[source][source + V] = 2;
            capacity[V][sink] = 2;

            int a, b, c;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                links[a + V].add(b);
                links[b].add(a + V);
                cost[a + V][b] = c;
                cost[b][a + V] = -c;
                capacity[a + V][b] = 1;
            }

            int ans = 0;
            while (true) {
                Queue<Integer> q = new LinkedList<>();
                prev = new int[V*2 + 1];
                contains = new boolean[V*2 + 1];
                Arrays.fill(dist, Integer.MAX_VALUE);

                q.add(source);
                contains[source] = true;
                dist[source] = 0;
                int u;
                while (!q.isEmpty()) {
                    u = q.poll();
                    contains[u] = false;

                    for (int v : links[u]) {
                        int w = dist[u] + cost[u][v];
                        if (capacity[u][v] - flow[u][v] > 0 && dist[v] > w) {
                            dist[v] = w;
                            prev[v] = u;

                            if (!contains[v]) {
                                q.add(v);
                                contains[v] = true;
                            }
                        }
                    }
                }

                // 증가 경로가 없으면 반복문 탈출
                if (prev[sink] == 0) {
                    break;
                }

                // 도착점에서 유량들 cost 더해 줌
                for (int v = sink; v != source; v = prev[v]) {
                    ans += cost[prev[v]][v];
                    flow[prev[v]][v]++;
                    flow[v][prev[v]]--;
                }
            }

            bw.write(ans + "\n");
        }
        bw.close();
    }
}
