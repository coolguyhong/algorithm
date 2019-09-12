package graph.baekjoon.fire;

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

    private static int w, h, sw, sh, fire;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[] fw, fh;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/5427
    // bfs 최단 경로
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h+1][w+1];
            visited = new int[h+1][w+1];
            fw = new int[h*w];
            fh = new int[h*w];

            fire = 0;
            for (int i = 1; i <= h; i++) {
                String str = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = str.charAt(j-1);
                    if (map[i][j] == '@') {
                        sh = i;
                        sw = j;
                    } else if (map[i][j] == '*') {
                        fh[fire] = i;
                        fw[fire] = j;
                        fire++;
                    }
                }
            }

            int ans = bfs();
            if (ans == 0) {
                bw.write("IMPOSSIBLE\n");
            } else {
                bw.write(ans + "\n");
            }

        }
        bw.close();

    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < fire; i++) {
            q.add(new int[]{fh[i], fw[i], 0, 0});
        }
        q.add(new int[]{sh, sw, 0, 1});
        visited[sh][sw]++;

        int[] n;
        while (!q.isEmpty()) {
            n = q.poll();

            if (n[3] == 1 && (n[0] == 1 || n[0] == h || n[1] == 1 || n[1] == w)) {
                return n[2] + 1;
            }

            for (int i = 0; i < 4; i++) {
                int th = n[0] + dx[i];
                int tw = n[1] + dy[i];

                if (th < 1 || th > h || tw < 1 || tw > w) {
                    continue;
                }

                if (map[th][tw] == '#' || map[th][tw] == '*') {
                    continue;
                }
                if (visited[th][tw] != 0) {
                    continue;
                }

                if (n[3] == 1) {
                    visited[th][tw]++;
                } else {
                    map[th][tw] = '*';
                }

                q.add(new int[]{th, tw, n[2] + 1, n[3]});
            }
        }

        return 0;
    }
}