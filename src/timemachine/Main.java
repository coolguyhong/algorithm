package timemachine;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[] D;
    private static int[][] path;
    private static final int MAX_VALUE = 5000000;

    // 백준 알고리즘 타임머신 11657
    // 벨만 포드 무어 알고리즘
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        D = new int[N+1];
        for (int i = 1; i <= N ; i++) {
            D[i] = MAX_VALUE;
        }

        path = new int[M][3];
        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            path[i][0] = a;
            path[i][1] = b;
            path[i][2] = c;
        }

        bellmanFordMoore();
    }

    private static void bellmanFordMoore() throws IOException {
        D[1] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (D[path[j][0]] != MAX_VALUE && D[path[j][1]] > D[path[j][0]] + path[j][2]) {
                    D[path[j][1]] = D[path[j][0]] + path[j][2];
                }
            }
        }
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int j = 0; j < M; j++) {
            if (D[path[j][0]] != MAX_VALUE && D[path[j][1]] > D[path[j][0]] + path[j][2]) {
                bw.write("-1\n");
                bw.flush();
                bw.close();
                return;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (D[i] == MAX_VALUE) {
                bw.write("-1\n");
            } else {
                bw.write(D[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
