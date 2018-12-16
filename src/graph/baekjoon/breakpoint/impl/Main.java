package graph.baekjoon.breakpoint.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, E;
    private static List<Integer>[] links;
    private static boolean[] isCutV;
    private static int[] visitedOrder;
    private static int order;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        links = new ArrayList[V+1];
        isCutV = new boolean[V+1];
        visitedOrder = new int[V+1];

        for (int i = 1; i <= V; i++) {
            links[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            links[a].add(b);
            links[b].add(a);
        }

        order = 0;
        for (int i = 1; i <= V; i++) {
            if (visitedOrder[i] != 0) {
                continue;
            }

            dfs(i, -1);
        }

        int cnt = 0;
        String ans = "";
        for (int i = 1; i <= V; i++) {
            if (isCutV[i]) {
                cnt++;
                ans += i + " ";
            }
        }

        bw.write(cnt + "\n");
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    private static int dfs(int node, int parent) {
        if (visitedOrder[node] != 0) {
            return visitedOrder[node];
        }

        order++;
        visitedOrder[node] = order;
        int minLow = visitedOrder[node];

        int child = 0;
        for (int c : links[node]) {
            if (c == parent) {
                continue;
            }

            if (visitedOrder[c] != 0) {
                minLow = Math.min(minLow, visitedOrder[c]);
            } else {
                child++;
                int low = dfs(c, node);

                if (parent != -1 && low >= visitedOrder[node]) {
                    isCutV[node] = true;
                }

                minLow = Math.min(low, minLow);
            }
        }

        if (parent == -1 && child > 1) {
            isCutV[node] = true;
        }

        return minLow;
    }
}
