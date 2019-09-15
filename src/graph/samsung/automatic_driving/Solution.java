package graph.samsung.automatic_driving;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, S, E;
    private static int[] D;
    private static int[][] path;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= C; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            D = new int[N+1];
            path = new int[M][3];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                path[i][0] = Integer.parseInt(st.nextToken());
                path[i][1] = Integer.parseInt(st.nextToken());
                path[i][2] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(path, (a, b) -> Integer.compare(a[2], b[2]));

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            int ans = Integer.MAX_VALUE;
            for (int start = 0; start < M; start++) {
                if (start > 0 && path[start-1][2] == path[start][2]) {
                    continue;
                }

                for (int i = 1; i <= N; i++) {
                    D[i] = i;
                }

                for (int i = start; i < M; i++) {
                    int pa = find(path[i][0], D);
                    int pb = find(path[i][1], D);
                    if (pa != pb) {
                        D[pb] = pa;
                        if (find(S, D) == find(E, D)) {
                            ans = Math.min(ans, path[i][2] - path[start][2]);
                            break;
                        }
                    }
                }

                if (find(S, D) != find(E, D)) {
                    break;
                }
            }

            bw.write("#" + testCase + " " + ans + "\n");
        }
        bw.close();
    }

    private static int find(int a, int[] d) {
        if (d[a] == a) {
            return a;
        } else {
            return d[a] = find(d[a], d);
        }
    }
}
