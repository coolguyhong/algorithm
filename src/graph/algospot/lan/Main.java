package graph.algospot.lan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] X = new int[500];
    static int[] Y = new int[500];

    static boolean[] added = new boolean[500];
    static double[] minWeight = new double[500];
    static int[] parent = new int[500];

    static double[][] adj = new double[500][500];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());

        while (cases-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                X[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++)
                Y[i] = Integer.parseInt(st.nextToken());

            for(int i=0; i<N; i++) {
                Arrays.fill(adj[i], 0.0);
            }

            Arrays.fill(added, false);
            Arrays.fill(minWeight, 987654321.0);
            Arrays.fill(parent, -1);

            int start = 0, end = 0;

            for(start=0; start<N; start++) {
                for(end=start+1; end<N; end++) {
                    double dist = getMinDistance(start, end);
                    adj[start][end] = dist;
                    adj[end][start] = dist;
                }
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());

                adj[start][end] = 0.0;
                adj[end][start] = 0.0;
            }

            System.out.printf("%.8f\n", prim());
        }

        br.close();
    }

    private static double prim() {

        double ret = 0.0;

        minWeight[0] = 0.0;
        parent[0] = 0;

        for(int i=0; i<N; i++) {

            int u = -1;

            for(int v=0; v<N; v++) {
                if(!added[v] && (u == -1 || minWeight[u] > minWeight[v]))
                    u = v;
            }

            ret += minWeight[u];
            added[u] = true;

            for(int v = 0; v < N; v++) {
                if(!added[v] && minWeight[v] > adj[u][v]) {
                    parent[v] = u;
                    minWeight[v] = adj[u][v];
                }
            }
        }

        return ret;
    }


    private static double getMinDistance(int cur, int i) {
        double y2 = Y[cur]- Y[i];
        double x2 = X[cur]- X[i];
        return Math.sqrt(y2*y2 + x2*x2);
    }
}