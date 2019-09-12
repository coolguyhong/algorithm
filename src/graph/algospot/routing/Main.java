package graph.algospot.routing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static double[] D;
    private static Node[] nodes;

    // 알고스팟 연습문제
    // https://algospot.com/judge/problem/read/ROUTING
    // 신호 라우팅(ROUTING, 난이도 하): 최단거리 구하기, 다익스트라
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            D = new double[N];
            nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                D[i] = Double.MAX_VALUE;
                nodes[i] = new Node(i);
            }

            int a, b;
            double d;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Double.parseDouble(st.nextToken());

                nodes[a].addLink(new Link(nodes[b], d));
                nodes[b].addLink(new Link(nodes[a], d));
            }

            dijkstra();

            bw.write(D[N-1] + "\n");
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));
        pq.add(new double[]{0, 1});
        D[0] = 1;

        double[] d;
        while (!pq.isEmpty()) {
            d = pq.poll();
            if (D[(int) d[0]] < d[1]) {
                continue;
            }

            for (Link link : nodes[(int) d[0]].links) {
                if (D[link.target.no] > link.weight * D[(int) d[0]]) {
                    D[link.target.no] = link.weight * D[(int) d[0]];
                    pq.add(new double[]{link.target.no, D[link.target.no]});
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
    double weight;

    Link(Node node, double weight) {
        this.target = node;
        this.weight = weight;
    }
}