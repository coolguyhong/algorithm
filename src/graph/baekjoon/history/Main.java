package graph.baekjoon.history;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n, k, s;
    private static int [][] D;
    private static final int max = 40000;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/1613
    // 역사, 플로이드 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        D = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                D[i][j] = max;
            }
        }

        int a, b;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            D[a][b] = 2;
            D[b][a] = 1;
        }

        floyd();

        s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (D[a][b] == 2) {
                bw.write("-1\n");
            } else if (D[a][b] == 1) {
                bw.write("1\n");
            } else if (D[a][b] == max) {
                bw.write("0\n");
            }
        }

        bw.close();
    }

    private static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    if (D[s][e] > D[s][k] + D[k][e]) {
                        if (D[s][k] + D[k][e] == 4) {
                            D[s][e] = 2;
                            D[e][s] = 1;
                        } else if (D[s][k] + D[k][e] == 2) {
                            D[s][e] = 1;
                            D[e][s] = 2;
                        }
                    }
                }
            }
        }
    }
}