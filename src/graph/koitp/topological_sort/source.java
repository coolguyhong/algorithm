package graph.koitp.topological_sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E;
    private static List<Integer>[] links;
    private static int[] indegrees;
    private static List<Integer> ans;

    // KOITP
    // https://koitp.org/problem/TOPOLOGICAL_SORT/read/
    // 위상정렬, 그래프, topological_sort
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        links = new ArrayList[V+1];
        indegrees = new int[V+1];
        for (int i = 1; i <= V; i++) {
            links[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            indegrees[b]++;
            links[a].add(b);
        }

        ans = new ArrayList<>();
        topological_sort();

        for (int n : ans) {
            bw.write(n + " ");
        }
        bw.newLine();
        bw.close();
    }

    private static void topological_sort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= V; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
                ans.add(i);
            }
        }

        int n;
        while (!q.isEmpty()) {
            n = q.poll();

            for (int c : links[n]) {
                indegrees[c]--;
                if (indegrees[c] == 0) {
                    q.add(c);
                    ans.add(c);
                }
            }
        }
    }
}
