package graph.algospot.timetrip;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int V, W;
    private static int[] min_d;
    private static int[] max_d;
    private static final int max_value = Integer.MAX_VALUE;
    private static int[][] min_path;
    private static int[][] max_path;

    // 알고스팟 문제 해결 능력
    // https://algospot.com/judge/problem/read/TIMETRIP
    // 시간여행(TIMETRIP): 최단거리, 벨만포드 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            min_d = new int[V];
            Arrays.fill(min_d, max_value);
            
            max_d = new int[V];
            Arrays.fill(max_d, max_value);
            
            min_path = new int[W][3];
            max_path = new int[W][3];
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

            min_d[0] = 0;
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < W; j++) {
                    if (min_d[min_path[j][0]] != max_value && min_d[min_path[j][1]] > min_d[min_path[j][0]] + min_path[j][2]) {
                        min_d[min_path[j][1]] = min_d[min_path[j][0]] + min_path[j][2];
                    }
                }
            }

            if (min_d[1] == max_value) {
                System.out.println("UNREACHABLE");
                continue;
            }

            StringBuffer sb = new StringBuffer();
            boolean isInfinity = false;
            for (int j = 0; j < W; j++) {
                if (min_d[min_path[j][0]] != max_value && min_d[min_path[j][1]] > min_d[min_path[j][0]] + min_path[j][2]) {
                    sb.append("INFINITY ");
                    isInfinity = true;
                    break;
                }
            }

            if (!isInfinity) {
                sb.append(min_d[1] + " ");
            }

            max_d[0] = 0;
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < W; j++) {
                    if (max_d[max_path[j][0]] != max_value && max_d[max_path[j][1]] > max_d[max_path[j][0]] + max_path[j][2]) {
                        max_d[max_path[j][1]] = max_d[max_path[j][0]] + max_path[j][2];
                    }
                }
            }

            isInfinity = false;
            for (int j = 0; j < W; j++) {
                if (max_d[max_path[j][0]] != max_value && max_d[max_path[j][1]] > max_d[max_path[j][0]] + max_path[j][2]) {
                    sb.append("INFINITY");
                    isInfinity = true;
                    break;
                }
            }

            if (!isInfinity) {
                sb.append((max_d[1] * -1));
            }

            System.out.println(sb);
        }
    }
}
