package graph.baekjoon.electronic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int m, n;
    private static int[] D;
    private static int[][] path;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            D = new int[m];
            for (int i = 0; i < m; i++) {
                D[i] = i;
            }

            path = new int[n][3];
            int a, b, c;
            long ans = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                ans += c;
                path[i][0] = a;
                path[i][1] = b;
                path[i][2] = c;
            }

            Arrays.sort(path, (p1, p2) -> Integer.compare(p1[2], p2[2]));

            int cnt = 0;
            long ans2 = 0;
            for (int i = 0; i < n; i++) {
                int pa = find(path[i][0]);
                int pb = find(path[i][1]);

                if (pa == pb) {
                    continue;
                }

                D[pa] = pb;
                ans2 += path[i][2];
                cnt++;

                if (cnt == m-1) {
                    break;
                }
            }

            bw.write((ans - ans2) + "\n");
        }
        bw.close();
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }
}
