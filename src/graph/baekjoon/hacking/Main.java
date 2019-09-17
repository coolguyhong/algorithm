package graph.baekjoon.hacking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n, d, c;
    private static Node[] nodes;
    private static int[] D;
    private static final int max = 100000001;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/10282
    // 해킹, 다익스트라 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            nodes = new Node[n+1];
            D = new int[n+1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new Node(i);
                D[i] = max;
            }

            int a, b, s;
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());

                nodes[b].addLink(new Link(nodes[a], s));
            }

            dijkstra();
            int cnt = 0;
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (D[i] == max) {
                    continue;
                }
                cnt++;
                if (ans < D[i]) {
                    ans = D[i];
                }
            }

            bw.write(cnt + " " + ans + "\n");
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{c, 0});
        D[c] = 0;

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();

            if (D[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (D[link.target.no] > link.weight + D[n[0]]) {
                    D[link.target.no] = link.weight + D[n[0]];
                    pq.add(new int[]{link.target.no, link.weight + D[n[0]]});
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