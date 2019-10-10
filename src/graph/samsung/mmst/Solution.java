package graph.samsung.mmst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, S;
    private static Node[] nodes;
    private static long[] dist;
    private static int[] minWeight;

    //
    //
    //
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nodes = new Node[N];
        dist = new long[N];
        minWeight = new int[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
            dist[i] = Long.MAX_VALUE;
            minWeight[i] = Integer.MAX_VALUE;
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            nodes[a].add(new Link(nodes[b], c));
            nodes[b].add(new Link(nodes[a], c));
        }

        for (int i = 0; i < N; i++) {
            Collections.sort(nodes[i].links, (l1, l2) -> Integer.compare(l1.weight, l2.weight));
        }

        dijkstra();

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += minWeight[i];
        }

        bw.write(ans + "\n");
        bw.close();

    }

    private static void dijkstra() {
        Queue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{S, 0});
        minWeight[S] = 0;
        dist[S] = 0;

        long[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();
            int cur = (int) n[0];
            long d = n[1];

            if (dist[cur] < d) {
                continue;
            }

            for (Link link: nodes[cur].links) {
                if (dist[link.to.no] >= link.weight + dist[cur]) {
                    dist[link.to.no] = link.weight + dist[cur];
                    if (minWeight[link.to.no] > link.weight) {
                        minWeight[link.to.no] = link.weight;
                        pq.add(new long[]{link.to.no, dist[link.to.no]});
                    } else {
                        System.out.println("test");
                    }
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

    public void add(Link link) {
        links.add(link);
    }
}

class Link {
    Node to;
    int weight;

    public Link(Node to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
