package graph.koitp.silver_cowparty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, X;
    private static Node[] nodes;
    private static Node[] reverseNodes;
    private static int[] D1;
    private static int[] D2;
    private static final int max_value = 10000001;

    // koitp.org
    // cowparty: dijkstra
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        nodes = new Node[N+1];
        reverseNodes = new Node[N+1];
        D1 = new int[N+1];
        D2 = new int[N+1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
            reverseNodes[i] = new Node(i);
            D1[i] = max_value;
            D2[i] = max_value;
        }

        int a, b, t;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            nodes[a].addLink(new Link(nodes[b], t));
            reverseNodes[b].addLink(new Link(nodes[a], t));
        }

        fromDesToStartDijkstra();
        fromStartToDesDijkstra();

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = max(max, D1[i] + D2[i]);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static void fromStartToDesDijkstra() {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        D2[X] = 0;
        pq.add(new int[]{X, D2[X]});

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();
            if (D2[n[0]] < n[1]) {
                continue;
            }

            for (Link link : reverseNodes[n[0]].links) {
                if (D2[link.target.no] > D2[n[0]] + link.time) {
                    D2[link.target.no] = D2[n[0]] + link.time;
                    pq.add(new int[]{link.target.no, D2[link.target.no]});
                }
            }
        }
    }

    private static void fromDesToStartDijkstra() {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        D1[X] = 0;
        pq.add(new int[]{X, D1[X]});

        int[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();
            if (D1[n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[n[0]].links) {
                if (D1[link.target.no] > D1[n[0]] + link.time) {
                    D1[link.target.no] = D1[n[0]] + link.time;
                    pq.add(new int[]{link.target.no, D1[link.target.no]});
                }
            }
        }
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
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
    int time;

    public Link(Node node, int time) {
        this.target = node;
        this.time = time;
    }
}