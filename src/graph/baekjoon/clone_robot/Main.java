package graph.baekjoon.clone_robot;

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
    private static int[] parents;
    private static char[][] maze;
    private static Deque<Node> nodes;
    private static List<Edge> edges;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] node;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/1944
    // 복제 로봇, bfs 활용
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N+1][N+1];
        nodes = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                char c = str.charAt(j-1);
                maze[i][j] = c;
                if (maze[i][j] == 'S') {
                    nodes.addFirst(new Node(i, j));
                } else if (maze[i][j] == 'K') {
                    nodes.add(new Node(i, j));
                }
            }
        }

        node = new int[N+1][N+1];
        int idx = 1;
        for (Node n : nodes) {
            node[n.x][n.y] = idx++;
        }

        edges = new ArrayList<>();
        for (Node n : nodes) {
            bfs(n.x, n.y);
        }

        Collections.sort(edges);

        parents = new int[M+2];
        for (int i = 1; i <= M+1; i++) {
            parents[i] = i;
        }

        int cnt = 0;
        int ans = 0;
        for (Edge edge : edges) {
            int ps = find(edge.s);
            int pe = find(edge.e);
            if (ps == pe) {
                continue;
            }

            parents[pe] = ps;
            cnt++;
            ans += edge.c;

            if (cnt == M) {
                break;
            }
        }

        if (cnt < M) {
            bw.write("-1\n");
        } else {
            bw.write(ans + "\n");
        }
        bw.close();
    }

    private static int find(int s) {
        if (parents[s] == s) {
            return s;
        } else {
            return parents[s] = find(parents[s]);
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        int[][] visited = new int[N+1][N+1];
        visited[x][y]++;

        int[] n;
        while (!q.isEmpty()) {
            n = q.poll();
            int cx = n[0];
            int cy = n[1];
            int d = n[2];

            for (int i = 0; i < 4; i++) {
                int tx = cx + dx[i];
                int ty = cy + dy[i];

                if (tx < 1 || tx > N || ty < 1 || ty > N) {
                    continue;
                }

                if (visited[tx][ty] != 0 || maze[tx][ty] == '1') {
                    continue;
                }

                visited[tx][ty]++;
                q.add(new int[]{tx, ty, d+1});
                if (maze[tx][ty] == 'S' || maze[tx][ty] == 'K') {
                    edges.add(new Edge(node[x][y], node[tx][ty], d+1));
                }
            }
        }
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int s, e, c;
    Edge(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.c, o.c);
    }
}
