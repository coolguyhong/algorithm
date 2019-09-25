package graph.samsung.undercity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K;
    private static int[] links, visited, starts, dist;

    // [기출P-0014] 지하도시의 친구들
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            links = new int[N + 1];
            visited = new int[N + 1];
            starts = new int[N + 1];
            dist = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                links[i] = Integer.parseInt(st.nextToken());
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                int index = 1;
                int next = links[i];
                if (next == i) { // 자식이 본인일 경우
                    dist[i] = 0;
                    visited[i] = 1;
                    continue;
                }

                while (true) {
                    if (visited[next] > 0) { // 이미 방문했던 곳은 탐색 하지 않음
                        break;
                    }
                    if (next == i) { //싸이클이 형성이 됨
                        dist[i] = --index;
                        visited[i] = 1;
                        while (!q.isEmpty()) {
                            dist[q.poll()] = index;
                        }
                        break;
                    }
                    if (index > K) {
                        break;
                    }
                    index++;
                    visited[next] = index;
                    q.add(next);
                    next = links[next];
                }

                while (!q.isEmpty()) {
                    visited[q.poll()] = 0;
                }
            }

            for (int i = 1; i <= N; i++) {
                if (visited[i] != 0) {
                    continue;
                }

                int index = 1;
                int next = links[i];
                while (index <= K) {
                    dist[next]++;
                    index++;
                    next = links[next];
                }
            }

            long ans = 0;
            for (int i = 1; i <= N; i++) {
                ans += dist[i];
            }
            bw.write(ans + "\n");
        }
        bw.close();
    }
}