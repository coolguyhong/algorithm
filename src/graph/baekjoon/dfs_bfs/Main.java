package graph.baekjoon.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N, M, V;
    private static List<Integer>[] links;
    private static int[] visited;

    // 백준 알고리즘
    // dfs와 bfs(1260) 그래프
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        
        links = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            links[a].add(b);
            links[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(links[i]);
        }

        visited = new int[N+1];
        dfs(V);

        System.out.println();

        visited = new int[N+1];
        bfs();
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(V);
        visited[V] = 1;

        int n;
        while (!q.isEmpty()) {
            n = q.poll();
            System.out.print(n + " ");
            for (int c : links[n]) {
                if (visited[c] != 0) {
                    continue;
                }
                visited[c] = 1;
                q.add(c);
            }
        }
    }

    private static void dfs(int v) {
        if (visited[v] != 0) {
            return;
        }
        visited[v] = 1;
        System.out.print(v + " ");

        for (int c : links[v]) {
            if (visited[c] != 0) {
                continue;
            }
            dfs(c);
        }
    }
}
