package graph.koitp.shortest_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static long[] D;
    private static Node[] nodes;
    private static final long max_value = 100000000001L;

    // dijkstra 알고리즘 최단거리 구하기
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        D = new long[N+1];
        nodes = new Node[N+1];

        for (int i = 1; i <= N; i++) {
            D[i] = max_value;
            nodes[i] = new Node(i);
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            nodes[a].addLink(new Link(nodes[b], c));
            nodes[b].addLink(new Link(nodes[a], c));
        }

        dijkstra();

        bw.write(((D[N] == max_value) ? -1 : D[N]) + "\n");
        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        Queue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[1], o2[1]);
            }
        });

        D[1] = 0;
        pq.add(new long[]{1, D[1]});

        long[] n;
        while (!pq.isEmpty()) {
            n = pq.poll();
            // 최소거리를 기록하는 배열이 더 작을 경우 알아볼 필요 없음
            if (D[(int) n[0]] < n[1]) {
                continue;
            }

            for (Link link : nodes[(int) n[0]].links) {
                if (D[link.target.no] > D[(int) n[0]] + link.time) {
                    D[link.target.no] = D[(int) n[0]] + link.time;
                    pq.add(new long[]{link.target.no, D[link.target.no]});
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
    int time;

    public Link(Node node, int time) {
        this.target = node;
        this.time = time;
    }
}
