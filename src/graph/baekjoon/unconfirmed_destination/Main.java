package graph.baekjoon.unconfirmed_destination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n, m, t, s, g, h;
    private static final int max = 50000001;
    private static Node[] nodes;
    private static int[] D, E;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/9370
    // 미확인 도착지,
    // 해당 경로를 지나갔다고 하면 모든 경로 짝수로 두고
    // 해당 경로만 홀수로 둠
    // 만약 최단 거리 목적지 후보지가 홀수면 해당 경로 지난것
    // 아니면 안지난것
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            nodes = new Node[n+1];
            D = new int[n+1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new Node(i);
                D[i] = max;
            }

            int a, b, d;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                nodes[a].addLink(new Link(nodes[b], d * 2));
                nodes[b].addLink(new Link(nodes[a], d * 2));
            }

            for (Link link : nodes[g].links) {
                if (link.target.no == h) {
                    link.weight--;
                    break;
                }
            }
            for (Link link : nodes[h].links) {
                if (link.target.no == g) {
                    link.weight--;
                    break;
                }
            }

            E = new int[t];
            for (int i = 0; i < t; i++) {
                E[i] = Integer.parseInt(br.readLine());
            }

            dijkstra();

            Arrays.sort(E);
            for (int i = 0; i < t; i++) {
                if (D[E[i]] == max) {
                    continue;
                }
                if (D[E[i]] % 2 == 1) {
                    bw.write(E[i] + " ");
                }
            }
            bw.newLine();
        }
        bw.close();

    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{s, 0});
        D[s] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (D[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (D[link.target.no] > D[n[0]] + link.weight) {
                    D[link.target.no] = D[n[0]] + link.weight;
                    pq.add(new int[]{link.target.no, D[n[0]] + link.weight});
                }
            }
        }
    }
}

class Node {
    int no;
    List<Link> links;

    Node(int no) {
        this.no = no;
        links = new ArrayList<>();
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