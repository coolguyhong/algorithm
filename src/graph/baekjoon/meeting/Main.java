package graph.baekjoon.meeting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] D;
    private static int[] max, visited;
    private static final int INF = 100000001;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/2610
    // 회의준비, 플로이드 와샬 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        D = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                D[i][j] = INF;
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

        // 각 노드에서 다른 노드로 연결된 노드로의 최대 값
        max = new int[N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                if (D[i][j] == INF) {
                    continue;
                }

                if (D[i][j] > max[i]) {
                    max[i] = D[i][j];
                }
            }
        }

        visited = new int[N+1];
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (visited[i] != 0) {
                continue;
            }
            int t = i;
            for (int j = 1; j <= N; j++) {
                if (D[i][j] == INF) {
                    continue;
                }
                visited[j]++;
                if (max[t] > max[j]) {
                    t = j;
                }
            }

            ans.add(t);
        }

        bw.write(ans.size() + "\n");
        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++) {
            bw.write(ans.get(i) + "\n");
        }
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
