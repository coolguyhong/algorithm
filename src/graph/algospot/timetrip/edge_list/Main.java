package graph.algospot.timetrip.edge_list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, W;
    private static int[][] min_path, max_path;
    private static int[] min_d, max_d;
    private static final int INF = 1000001;

    /// 알고스팟 문제 해결 능력
    // https://algospot.com/judge/problem/read/TIMETRIP
    // 시간여행(TIMETRIP): 최단거리, 벨만포드 알고리즘, 간선리스트로 풀기
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            min_d = new int[V];
            max_d = new int[V];
            max_path = new int[W][3];
            min_path = new int[W][3];

            int a, b, d;
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                min_path[i][0] = a;
                min_path[i][1] = b;
                min_path[i][2] = d;

                max_path[i][0] = a;
                max_path[i][1] = b;
                max_path[i][2] = d * -1;
            }

            for (int i = 0; i < V; i++) {
                min_d[i] = INF;
                max_d[i] = INF;
            }

            min_d[0] = 0;
            for (int j = 0; j < V; j++) {
                for (int i = 0; i < W; i++) {
                    if (min_d[min_path[i][0]] != INF && min_d[min_path[i][1]] > min_d[min_path[i][0]] + min_path[i][2]) {
                        min_d[min_path[i][1]] = min_d[min_path[i][0]] + min_path[i][2];
                    }
                }
            }
            if (min_d[1] == INF) {
                bw.write("UNREACHABLE\n");
                continue;
            }

            boolean isInfinity = false;
            for (int i = 0; i < W; i++) {
                if (min_d[min_path[i][0]] != INF && min_d[min_path[i][1]] > min_d[min_path[i][0]] + min_path[i][2]) {
                    isInfinity = true;
                    break;
                }
            }

            if (isInfinity) {
                bw.write("INFINITY ");
            } else {
                bw.write(min_d[1] + " ");
            }

            max_d[0] = 0;
            for (int j = 0; j < V; j++) {
                for (int i = 0; i < W; i++) {
                    if (max_d[max_path[i][0]] != INF && max_d[max_path[i][1]] > max_d[max_path[i][0]] + max_path[i][2]) {
                        max_d[max_path[i][1]] = max_d[max_path[i][0]] + max_path[i][2];
                    }
                }
            }
            if (max_d[1] == INF) {
                bw.write("UNREACHABLE\n");
                continue;
            }

            isInfinity = false;
            for (int i = 0; i < W; i++) {
                if (max_d[max_path[i][0]] != INF && max_d[max_path[i][1]] > max_d[max_path[i][0]] + max_path[i][2]) {
                    isInfinity = true;
                    break;
                }
            }

            if (isInfinity) {
                bw.write("INFINITY ");
            } else {
                bw.write((max_d[1] * -1) + " ");
            }
            bw.newLine();
        }
        bw.close();
    }
}
