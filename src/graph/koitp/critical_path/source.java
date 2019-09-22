package graph.koitp.critical_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static List<long[]>[] links;
    private static int[] indegrees;
    private static long[] D;

    // KOITP
    // https://koitp.org/problem/CRITICAL_PATH/read/
    // 임계경로, critical_path, topological_sort
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        links = new ArrayList[N+1];
        indegrees = new int[N+1];
        D = new long[N+1];

        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        long a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            links[(int) a].add(new long[]{b, c});
            indegrees[(int) b]++;
        }

        topological_sort();

        bw.write(D[N] + "\n");
        bw.close();
    }

    private static void topological_sort() {
        Queue<long[]> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == 0) {
                q.add(new long[]{i, 0});
                D[i] = 0;
            }
        }

        long[] n;
        while (!q.isEmpty()) {
            n = q.poll();

            for (long[] c : links[(int) n[0]]) {
                indegrees[(int) c[0]]--;
                if (D[(int) c[0]] < D[(int) n[0]] + c[1]) {
                    D[(int) c[0]] = D[(int) n[0]] + c[1];
                }
                if (indegrees[(int) c[0]] == 0) {
                    q.add(new long[]{c[0], D[(int) c[0]]});
                }
            }
        }
    }
}
