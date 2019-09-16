package graph.baekjoon.sangbeom_building;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int L, R, C, sx, sy, sz, ex, ey, ez;
    private static char[][][] map;
    private static int[][][] visited;
    private static int[] dx = {0, 0, 0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0, 0, 0};
    private static int[] dz = {0, 0, 1, -1, 0, 0};
    private static final int max = 100000;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/6593
    // 상범빌딩, 다익스트라(3차원)
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            map = new char[R+1][C+1][L+1];
            visited = new int[R+1][C+1][L+1];

            for (int z = 1; z <= L; z++) {
                for (int x = 1; x <= R; x++) {
                    String str = br.readLine();
                    for (int y = 1; y <= C; y++) {
                        char c = str.charAt(y-1);
                        map[x][y][z] = c;
                        if (c == 'S') {
                            sx = x;
                            sy = y;
                            sz = z;
                        } else if (c == 'E') {
                            ex = x;
                            ey = y;
                            ez = z;
                        }
                    }
                }
                br.readLine();
            }

            int ans = bfs();
            if (ans == max) {
                bw.write("Trapped!\n");
            } else {
                bw.write("Escaped in " + ans + " minute(s).\n");
            }
        }

        bw.close();
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, sz, 0});
        visited[sx][sy][sz]++;

        int[] n;
        while (!q.isEmpty()) {
            n = q.poll();

            int hereX = n[0];
            int hereY = n[1];
            int hereZ = n[2];
            if (hereX == ex && hereY == ey && hereZ == ez) {
                return n[3];
            }

            for (int i = 0; i < 6; i++) {
                int tz = hereZ + dz[i];
                int tx = hereX + dx[i];
                int ty = hereY + dy[i];

                if (tx < 1 || tx > R) {
                    continue;
                }
                if (ty < 1 || ty > C) {
                    continue;
                }
                if (tz < 1 || tz > L) {
                    continue;
                }

                if (map[tx][ty][tz] == '#') {
                    continue;
                }

                if (visited[tx][ty][tz] != 0) {
                    continue;
                }

                visited[tx][ty][tz]++;
                q.add(new int[]{tx, ty, tz, n[3] + 1});
            }
        }
        return max;
    }
}