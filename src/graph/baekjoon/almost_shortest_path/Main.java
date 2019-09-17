package graph.baekjoon.almost_shortest_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, S, D;
    private static Node[] nodes;
    private static List<Integer>[] trace;
    private static int[] dist;
    private static final int max = 10000001;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/5719
    // 거의 최단거리, 다익스트라
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            nodes = new Node[N];
            dist = new int[N];
            trace = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                nodes[i] = new Node(i);
                dist[i] = max;
                trace[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int u, v, p;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());

                nodes[u].addLink(new Link(nodes[v], p));
            }

            dijkstra();
            traceBack();
            Arrays.fill(dist, max);
            dijkstra();
            if (dist[D] == max) {
                bw.write("-1\n");
            } else {
                bw.write(dist[D] + "\n");
            }
        }

        bw.close();
    }

    private static void traceBack() {
        Queue<Integer> q = new LinkedList<>();
        q.add(D);

        int n;
        while (!q.isEmpty()) {
            n = q.poll();
            for (int pre : trace[n]) {
                int tempIdx = -1;
                for (int i = 0; i < nodes[pre].links.size(); i++) {
                    if (nodes[pre].links.get(i).target.no == n) {
                        tempIdx = i;
                        break;
                    }
                }
                if (tempIdx != -1 && dist[n] == dist[pre] + nodes[pre].links.get(tempIdx).weight) {
                    nodes[pre].links.remove(tempIdx); // 최단 경로 삭제
                    q.add(pre); // 추가로 연결된 최단 경로 탐색위해 Queue에 추가
                }
            }
        }
    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{S, 0});
        dist[S] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (dist[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (dist[link.target.no] >= dist[n[0]] + link.weight) {
                    dist[link.target.no] = dist[n[0]] + link.weight;
                    trace[link.target.no].add(n[0]);
                    pq.add(new int[]{link.target.no, dist[link.target.no]});
                }
            }
        }
    }
}

class Node {
    int no;
    List<Link> links = new ArrayList<>();

    Node(int no) {
        this.no = no;
    }

    void addLink(Link link) {
        links.add(link);
    }
}

class Link {
    Node target;
    int weight;

    Link(Node node, int weight) {
        this.target = node;
        this.weight = weight;
    }
}