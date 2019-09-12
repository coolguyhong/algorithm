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
    private static double[] dist;
    private static Node[] nodes;

    // 알고스팟 연습문제
    // https://algospot.com/judge/problem/read/ROUTING
    // 신호 라우팅(ROUTING, 난이도 하): 최단거리 구하기, 다익스트라
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            nodes = new Node[N];
            dist = new double[N];
            for (int i = 0; i < N; i++) {
                dist[i] = Double.MAX_VALUE;
                nodes[i] = new Node(i);
            }

            int a, b;
            double c;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Double.parseDouble(st.nextToken());

                nodes[a].addLink(new Link(nodes[b], c));
                nodes[b].addLink(new Link(nodes[a], c));
            }

            dijkstra();
            bw.write(dist[N-1] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        Queue<double[]> pq = new PriorityQueue<>((double[] a, double[] b) -> Double.compare(a[1], b[1]));
        dist[0] = 1;
        pq.add(new double[]{0, dist[0]});

        double[] d;
        while (!pq.isEmpty()) {
            d = pq.poll();
            if (dist[(int) d[0]] < d[1]) {
                continue;
            }

            for (Link link : nodes[(int) d[0]].links) {
                if (dist[link.target.no] > dist[(int) d[0]] * link.c) {
                    dist[link.target.no] = dist[(int) d[0]] * link.c;
                    pq.add(new double[]{link.target.no, dist[link.target.no]});
                }
            }
        }
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
    double c;

    public Link(Node node, double c) {
        target = node;
        this.c = c;
    }
}
