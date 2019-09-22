package graph.koitp.ghost_ship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int W, H, sx, sy, ex, ey;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    // koitp
    // https://koitp.org/problem/GHOST_SHIP/read/
    // 고스트쉽, bfs를 활용한 최단 거리 찾기
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H+1][W+1];
        visited = new int[H+1][W+1];

        for (int i = 1; i <= H; i++) {
            String str = br.readLine();
            for (int j = 1; j <= W; j++) {
                char c = str.charAt(j-1);
                map[i][j] = c;
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'E') {
                    ex = i;
                    ey = j;
                }
            }
        }

        int ans = bfs();
        bw.write(ans + "\n");
        bw.close();
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy]++;
        int ans = -1;

        int[] n;
        while (!q.isEmpty()) {
            n = q.poll();

            if (n[0] == ex && n[1] == ey) {
                return n[2];
            }

            for (int i = 0; i < 4; i++) {
                int tx = n[0] + dx[i];
                int ty = n[1] + dy[i];

                if (tx < 1 || tx > H || ty < 1 || ty > W) {
                    continue;
                }

                if (map[tx][ty] == 'X') {
                    continue;
                }

                if (visited[tx][ty] != 0) {
                    continue;
                }

                visited[tx][ty]++;
                q.add(new int[]{tx, ty, n[2] + 1});
            }
        }
        return ans;
    }
}
