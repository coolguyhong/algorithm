package graph.baekjoon.specific_shortest_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, E, N1, N2;
    private static Node[] nodes;
    private static final int max = 200000001;
    private static int[] D, M, F;
    // 백준 알고리즘(1504)
    // https://www.acmicpc.net/problem/1504
    // 특정한 최단 경로(다익스트라)
    // 특정 경로 지나야 한다면 시작점 ~ a, a ~ b, b ~ 목표점
    // 시작점 ~ b, b ~ a, b ~ 목표점 중의 최소 값
    // 다익스트라 3번으로 해결 가능
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        nodes = new Node[N+1];
        D = new int[N+1];
        M = new int[N+1];
        F = new int[N+1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
            D[i] = max;
            M[i] = max;
            F[i] = max;
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            nodes[a].addLink(new Link(nodes[b], c));
            nodes[b].addLink(new Link(nodes[a], c));
        }

        st = new StringTokenizer(br.readLine());
        N1 = Integer.parseInt(st.nextToken());
        N2 = Integer.parseInt(st.nextToken());

        dijkstra_1();
        dijkstra_2();
        dijkstra_3();

        int result = Math.min(D[N1] + M[N2] + F[N], D[N2] + F[N1] + M[N]);
        bw.write((result >= max ? -1 : result) + "\n");
        bw.close();
    }

    private static void dijkstra_3() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{N2, 0});
        F[N2] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (F[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (F[link.target.no] > F[n[0]] + link.weight) {
                    F[link.target.no] = F[n[0]] + link.weight;
                    pq.add(new int[]{link.target.no, F[link.target.no]});
                }
            }
        }
    }

    private static void dijkstra_2() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{N1, 0});
        M[N1] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (M[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (M[link.target.no] > M[n[0]] + link.weight) {
                    M[link.target.no] = M[n[0]] + link.weight;
                    pq.add(new int[]{link.target.no, M[link.target.no]});
                }
            }
        }
    }

    private static void dijkstra_1() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{1, 0});
        D[1] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (D[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (D[link.target.no] > D[n[0]] + link.weight) {
                    D[link.target.no] = D[n[0]] + link.weight;
                    pq.add(new int[]{link.target.no, D[link.target.no]});
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