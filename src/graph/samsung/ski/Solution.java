package graph.samsung.ski;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M, E;
    private static List<long[]>[] links;
    private static int[] indegrees;
    private static long[] D, score;
    private static List<Integer>[] path;
    private static long max;

    // sw 검정 09.21
    // 스키점수
    // 위상정렬(테스트 케이스가 없어 확실하지 않음
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            links = new ArrayList[N+1];
            D = new long[N+1];
            score = new long[N+1];
            indegrees = new int[N+1];
            path = new ArrayList[N+1];

            long c;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                c = Long.parseLong(st.nextToken());

                score[i] = c;
                D[i] = c;
                links[i] = new ArrayList<>();
                path[i] = new ArrayList<>();
            }

            int a, b;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                links[a].add(new long[]{b, score[b]});
                indegrees[b]++;
            }

            topological_sort();

            max = Long.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                if (D[i] > max) {
                    max = D[i];
                    E = i;
                }
            }

            removePath();
            max = Long.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                if (D[i] > max) {
                    max = D[i];
                }
            }

            bw.write("#" + testCase + " " + max + "\n");
        }
        bw.close();
    }

    private static void removePath() {
        long tempMax = Long.MIN_VALUE;
        for (int pre : path[E]) {
            if (D[pre] + score[E] == max) {
                continue;
            }
            if (D[pre] + score[E] > tempMax) {
                tempMax = D[pre] + score[E];
            }
        }

        D[E] = Math.max(tempMax, score[E]);
    }

    private static void topological_sort() {
        Queue<long[]> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == 0) {
                q.add(new long[]{i, 0});
            }
        }

        long[] n;
        while (!q.isEmpty()) {
            n = q.poll();

            for (long[] c : links[(int) n[0]]) {
                indegrees[(int) c[0]]--;
                if (D[(int) c[0]] <= D[(int) n[0]] + c[1]) {
                    D[(int) c[0]] = D[(int) n[0]] + c[1];
                    path[(int) c[0]].add((int) n[0]);
                }
                if (indegrees[(int) c[0]] == 0) {
                    q.add(new long[]{c[0], D[(int) c[0]]});
                }
            }
        }
    }
}