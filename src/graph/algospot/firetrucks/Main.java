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
    private static int[] D, n, m;
    private static final int max_value = 500000001;
    private static Node[] nodes;

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

            D = new int[V+1];
            nodes = new Node[V+1];
            for (int i = 1; i <= V; i++) {
                D[i] = max_value;
                nodes[i] = new Node(i);
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

            n = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                n[i] = Integer.parseInt(st.nextToken());
            }

            m = new int[M+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                m[i] = Integer.parseInt(st.nextToken());
            }

            dijkstra();

            int ans = 0;
            for (int i = 1; i <= N; i++) {
                ans += D[n[i]];
            }

            bw.write(ans + "\n");
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for (int i = 1; i <= M; i++) {
            D[m[i]] = 0;
            pq.add(new int[]{m[i], 0});
        }

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();
            if (D[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (D[link.target.no] > link.weight + D[n[0]]) {
                    D[link.target.no] = link.weight + D[n[0]];
                    pq.add(new int[]{link.target.no, D[link.target.no]});
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

    Link(Node target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}
