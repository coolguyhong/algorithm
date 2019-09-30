package graph.baekjoon.algospot;

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
    private static int[][] miro, visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    // 백준 알고리즘(1261)
    // https://www.acmicpc.net/problem/1261
    // 그래프 bfs, dequeue를 이용해서 벽을 뚫을 필요 없는 것들 먼저 방문, 그다음 벽 뚫는 거 방문
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[M+1][N+1];
        visited = new int[M+1][N+1];

        for (int i = 1; i <= M; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                miro[i][j] = Character.getNumericValue(str.charAt(j-1));
            }
        }

        bw.write(bfs() + "\n");
        bw.close();
    }

    private static int bfs() {
        Deque<int[]> pq = new ArrayDeque<>();
        pq.add(new int[]{1, 1, 0});
        visited[1][1]++;

        int[] n;
        int ans = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            n = pq.pollLast();
            if (n[0] == M && n[1] == N) {
                if (ans > n[2]) {
                    ans = n[2];
                }
            }

            for (int i = 0; i < 4; i++) {
                int tx = n[0] + dx[i];
                int ty = n[1] + dy[i];

                if (tx < 1 || tx > M || ty < 1 || ty > N) {
                    continue;
                }
                if (visited[tx][ty] != 0) {
                    continue;
                }

                visited[tx][ty]++;
                if (miro[tx][ty] == 1) {
                    pq.addFirst(new int[]{tx, ty, n[2] + 1});
                } else {
                    pq.addLast(new int[]{tx, ty, n[2]});
                }
            }
        }
        return ans;
    }
}
