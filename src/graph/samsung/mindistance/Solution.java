package graph.samsung.mindistance;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;
    private static Node[] nodes;
    private static int[] D;

    public static void main(String[] args) throws IOException {
        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            M = input.nextInt();

            nodes = new Node[N+1];
            D = new int[N+1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new Node(i);
                D[i] = Integer.MAX_VALUE;
            }

            int a, b, c;
            for (int i = 0; i < M; i++) {
                a = input.nextInt();
                b = input.nextInt();
                c = input.nextInt();

                nodes[a].add(new Link(nodes[b], c));
                nodes[b].add(new Link(nodes[a], c));
            }

            dijkstra();
            bw.write("#" + testCase + " " + (D[N] == Integer.MAX_VALUE ? -1 : D[N]) + "\n");
            bw.flush();
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        D[1] = 0;
        pq.add(new int[]{1, D[1]});
        int[] q;
        while (!pq.isEmpty()) {
            q = pq.poll();
            for (Link link : nodes[q[0]].links) {
                if (D[link.to.no] > D[q[0]] + link.time) {
                    D[link.to.no] = D[q[0]] + link.time;
                    pq.add(new int[]{link.to.no, D[link.to.no]});
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
    int time;

    public Link(Node to, int time) {
        this.to = to;
        this.time = time;
    }
}

class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}
