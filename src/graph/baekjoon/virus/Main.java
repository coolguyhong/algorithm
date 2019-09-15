package graph.baekjoon.virus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    
    private static int N, M;
    private static int[][] D;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/2606
    // 바이러스, 플로이드 와샬 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        D = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    D[i][j] = 0;
                } else {
                    D[i][j] = 100001;
                }
            }
        }
        
        M = Integer.parseInt(br.readLine());
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            
            D[a][b] = 1;
            D[b][a] = 1;
        }
        
        floyd();

        int ans = 0;
        for (int i = 2; i <= N; i++) {
            if (D[1][i] == 100001) {
                continue;
            }
            ans++;
        }

        bw.write(ans + "\n");
        bw.close();
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int s = 1; s <= N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (D[s][e] > D[s][k] + D[k][e]) {
                        D[s][e] = D[s][k] + D[k][e];
                    }
                }
            }
        }
    }
}