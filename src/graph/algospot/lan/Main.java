package graph.algospot.lan;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[] parents, X, Y;
    private static double[][] adj;
    private static boolean[] added;
    private static double[] minWeight;
    private static final double max = 987654321.0;

    // 알고스팟
    // https://algospot.com/judge/problem/read/LAN
    // 최스신장트리, 프림 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            X = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Integer.parseInt(st.nextToken());
            }

            Y = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Y[i] = Integer.parseInt(st.nextToken());
            }

            adj = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    double dist = getDistance(i, j);
                    adj[i][j] = dist;
                    adj[j][i] = dist;
                }
            }

            int a, b;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                adj[a][b] = 0.0;
                adj[b][a] = 0.0;
            }

            added = new boolean[N];
            minWeight = new double[N];
            parents = new int[N];

            Arrays.fill(minWeight, max);
            Arrays.fill(parents, -1);

            bw.write(prim() + "\n");
        }
        bw.close();
    }

    private static double prim() {
        double ans = 0.0;

        parents[0] = 0;
        minWeight[0] = 0;

        for (int i = 0; i < N; i++) {
            int u = -1;
            for (int v = 0; v < N; v++) {
                if (!added[v] && (u == -1 || minWeight[u] > minWeight[v])) {
                    u = v;
                }
            }

            ans += minWeight[u];
            added[u] = true;

            for (int v = 0; v < N; v++) {
                if (!added[v] && minWeight[v] > adj[u][v]) {
                    parents[v] = u;
                    minWeight[v] = adj[u][v];
                }
            }
        }

        return ans;
    }

    private static double getDistance(int i, int j) {
        double dx = X[i] - X[j];
        double dy = Y[i] - Y[j];

        return Math.sqrt(dx * dx + dy * dy);
    }
}