package graph.baekjoon.road_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K, D, max, min;
    private static Node[] nodes;
    private static int[] depth;
    private static int[][] parents, max_roads, min_roads;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/3176
    // 도로 네트워크(3176) : 두 도시를 연결하는 도로 중 가장 짧은 도로와 긴 도로를 구해라
    // lca 활용
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        depth = new int[N+1];
        nodes = new Node[N+1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        int n = N;
        D = 0;
        while (n > 0) {
            D++;
            n /= 2;
        }

        parents = new int[D][N+1];
        max_roads = new int[D][N+1];
        min_roads = new int[D][N+1];

        int a, b, c;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            nodes[a].addLink(new Link(nodes[b], c));
            nodes[b].addLink(new Link(nodes[a], c));
        }

        makeGraph();
        fillParents();

        K = Integer.parseInt(br.readLine());
        int d, e;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            lca(d, e);
            bw.write(min + " " + max + "\n");
        }
        bw.close();
    }

    private static void lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        int d = 0;
        max = -1;
        min = 1000001;
        while (diff > 0) {
            if (diff % 2 == 1) {
                max = max(max, max_roads[d][a]);
                min = min(min, min_roads[d][a]);
                a = parents[d][a];
            }

            diff /= 2;
            d++;
        }

        if (a == b) {
            return;
        }

        for (d = D-1; d >= 0; d--) {
            if (parents[d][a] == parents[d][b]) {
                continue;
            }

            max = max(max, max_roads[d][a]);
            max = max(max, max_roads[d][b]);
            min = min(min, min_roads[d][a]);
            min = min(min, min_roads[d][b]);
            a = parents[d][a];
            b = parents[d][b];
        }

        max = max(max, max_roads[0][a]);
        max = max(max, max_roads[0][b]);
        min = min(min, min_roads[0][a]);
        min = min(min, min_roads[0][b]);
    }

    private static void fillParents() {
        for (int d = 1; d < D; d++) {
            for (int i = 1; i <= N; i++) {
                parents[d][i] = parents[d-1][parents[d-1][i]];
                max_roads[d][i] = max(max_roads[d-1][parents[d-1][i]], max_roads[d-1][i]);
                min_roads[d][i] = min(min_roads[d-1][parents[d-1][i]], min_roads[d-1][i]);
            }
        }
    }

    private static void makeGraph() {
        Queue<Node> q = new LinkedList<>();
        q.add(nodes[1]);
        parents[0][1] = 0;
        depth[1] = 1;

        Node n;
        while (!q.isEmpty()) {
            n = q.poll();
            for (Link link : n.links) {
                if (parents[0][n.no] == link.target.no) {
                    continue;
                }
//                if (depth[link.target.no] != 0) {
//                    continue;
//                }

                depth[link.target.no] = depth[n.no] + 1;
                parents[0][link.target.no] = n.no;
                max_roads[0][link.target.no] = link.weight;
                min_roads[0][link.target.no] = link.weight;
                q.add(link.target);
            }
        }
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }
}

class Node {
    int no;
    List<Link> links = new ArrayList<>();

    public Node(int no) {
        this.no = no;
    }

    public void addLink(Link link) {
        links.add(link);
    }
}

class Link {
    Node target;
    int weight;

    public Link(Node node, int weight) {
        this.target = node;
        this.weight = weight;
    }
}
