package graph.koitp.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E, S;
    private static int[] visited;
    private static List<Integer>[] links;
    private static List<Integer> ans;

    // KOTIP
    // https://koitp.org/problem/BFS/read/
    // BFS 너비 우선 탐색
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        visited = new int[V+1];
        links = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            links[i] = new ArrayList<>();
        }

        int x, y;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            links[x].add(y);
            links[y].add(x);
        }

        ans = new ArrayList<>();
        bfs();

        for (int n : ans) {
            bw.write(n + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        visited[S]++;
        ans.add(S);

        int n;
        while (!q.isEmpty()) {
            n = q.poll();
            for (int c : links[n]) {
                if (visited[c] != 0) {
                    continue;
                }

                visited[c]++;
                ans.add(c);
                q.add(c);
            }
        }
    }
}
