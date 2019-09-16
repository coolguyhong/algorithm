package graph.baekjoon.jellda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[][] map, D;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static final int max = 2500;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/4485
    // 녹색 옷 입은 애가 젤다지? 다익스트라
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            testCase++;
            map = new int[N+1][N+1];
            D = new int[N+1][N+1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    D[i][j] = max;
                }
            }

            dijkstra();
            bw.write("Problem " + testCase + ":" + " " + D[N][N] + "\n");
        }
        bw.close();
    }

    private static void dijkstra() {
        Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        q.add(new int[]{1, 1, map[1][1]});
        D[1][1] = map[1][1];

        int[] n;
        while (!q.isEmpty()) {
            n = q.poll();

            if (D[n[0]][n[1]] < n[2]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int tx = n[0] + dx[i];
                int ty = n[1] + dy[i];

                if (tx < 1 || tx > N || ty < 1 || ty > N) {
                    continue;
                }

                if (D[tx][ty] > D[n[0]][n[1]] + map[tx][ty]) {
                    D[tx][ty] = D[n[0]][n[1]] + map[tx][ty];
                    q.add(new int[]{tx, ty, D[n[0]][n[1]] + map[tx][ty]});
                }
            }
        }
    }
}
