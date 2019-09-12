package graph.koitp.silver_cowpicnic;

import java.io.*;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int K, N, M;
    private static List<Integer>[] links;
    private static int[] k;
    private static int[] D;
    private static int[] visited;

    // koitp.org
    // https://koitp.org/problem/USACO_2006DEC_SILVER_COWPICNIC/read/
    // 피크닉(그래프): bfs 순회 돌면서 해당 정점에 몇번 들어가나
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        k = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            k[i] = Integer.parseInt(st.nextToken());
        }

        D = new int[N+1];
        links = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        int s, e;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            links[s].add(e);
        }

        for (int i = 0; i < K; i++) {
            visited = new int[N+1];
            bfs(k[i]);
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (D[i] == K) {
                ans++;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    private static void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        D[s]++;
        visited[s]++;

        int n;
        while (!q.isEmpty()) {
            n = q.poll();
            for (int c : links[n]) {
                if (visited[c] != 0) {
                    continue;
                }
                visited[c]++;
                D[c]++;
                q.add(c);
            }
        }
    }
}
