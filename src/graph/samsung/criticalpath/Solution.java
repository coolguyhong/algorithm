package graph.samsung.criticalpath;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;
    private static Node[] nodes;

    // 임계 경로
    public static void main(String[] args) throws IOException {
        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T ; testCase++) {
            N = input.nextInt();
            M = input.nextInt();

            nodes = new Node[N+1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new Node(i);
            }

            int a, b, c;
            for (int i = 0; i < M; i++) {
                a = input.nextInt();
                b = input.nextInt();
                c = input.nextInt();

                nodes[a].addLink(new Link(nodes[b], c));
            }

            // 위상정렬
            topological();

            bw.write("#" + testCase + " " + nodes[N].totalTime + "\n");
            bw.flush();
        }
        bw.close();
    }

    private static void topological() {
        Queue<Node> pq = new LinkedList<>();
        pq.add(nodes[1]);

        Node node;
        while (!pq.isEmpty()) {
            node = pq.poll();
            for (Link link: node.links) {
                link.des.totalTime = Math.max(link.des.totalTime, node.totalTime + link.time);
                link.des.inDegree--;
                if (link.des.inDegree == 0) {
                    pq.add(link.des);
                }
            }
        }
    }
}

class Node {
    int no;
    int inDegree;
    int totalTime;
    List<Link> links = new ArrayList<>();

    public Node(int no) {
        this.no = no;
        this.inDegree = 0;
    }

    public void addLink(Link link) {
        links.add(link);
        link.des.inDegree++;
    }
}

class Link {
    Node des;
    int time;

    public Link(Node des, int time) {
        this.des = des;
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
