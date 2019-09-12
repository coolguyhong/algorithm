package graph.algospot.nthlon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int M;
    private static int[] dist;
    private static Edge[] edges;
    private static final int V = 402;
    private static final int start = 200;
    private static final int max_value = Integer.MAX_VALUE;


    // 알고스팟 문제 해결 전략
    // https://algospot.com/judge/problem/read/NTHLON
    // 철인 N종 경기(NTHLON)
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            M = Integer.parseInt(br.readLine());

            edges = new Edge[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int aCost = Integer.parseInt(st.nextToken());
                int bCost =  Integer.parseInt(st.nextToken());

                edges[i] = new Edge(aCost - bCost, aCost);
            }

            dist = new int[V];
            Arrays.fill(dist, max_value);

            dijkstra();

            if (dist[401] == max_value) {
                bw.write("IMPOSSIBLE\n");
            } else {
                bw.write(dist[401] + "\n");
            }
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        Edge e;
        while (!pq.isEmpty()) {
            e = pq.poll();

            int here = e.to;
            for (int i = 0; i < M; i++) {
                int there = here + edges[i].to;
                if (there > 400 || there < 0) {
                    continue;
                }
                if (there == 200) {
                    there = 401;
                }
                if (dist[there] > dist[here] + edges[i].cost) {
                    dist[there] = dist[here] + edges[i].cost;
                    there = there == 401 ? 200 : there;
                    pq.add(new Edge(there, dist[here] + edges[i].cost));
                }

            }
        }
    }
}

class Edge {
    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}