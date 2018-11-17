package social_party;

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
    private static int[] D;
    private static int[] G;

    // 사내 알고리즘 문제
    // 사교모임
    // 주어진 마을이 같은 모임인지 판단
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            D = new int[N+1];
            G = new int[N+1];
            for (int i = 1; i <= N; i++) {
                D[i] = i;
            }

            int a, b;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            for (int i = 1; i <= N; i++) {
                G[D[i]]++;
            }
            int max = 0;
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (G[i] > 1) {
                    cnt++;
                    max = Math.max(max, G[i]);
                }
            }

            bw.write("#" + testCase + " " + cnt + " " + max + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == a && pb != b) {
            D[pa] = pb;
            changeRoot(pa, pb);
        } else if (pa != a && pb == b) {
            D[pb] = pa;
            changeRoot(pb, pa);
        } else {
            D[pb] = pa;
            changeRoot(pb, pa);
        }
    }

    private static void changeRoot(int pa, int pb) {
        for (int i = 1; i <= N; i++) {
            if (i == pb) {
                continue;
            }

            if (D[i] == pa) {
                D[i] = pb;
            }
        }
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }
}
