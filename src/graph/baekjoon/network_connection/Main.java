package graph.baekjoon.network_connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[] D, G;
    private static final int mod = 1000;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/3780
    // 네트워크 연결
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            D = new int[N+1];
            G = new int[N+1];
            for (int i = 1; i <= N; i++) {
                D[i] = i;
            }

            int center;
            int company;
            while (true) {
                st = new StringTokenizer(br.readLine());
                String flag = st.nextToken();
                if (flag.equals("O")) {
                    break;
                }

                if (flag.equals("E")) {
                    company = Integer.parseInt(st.nextToken());
                    find(company);
                    bw.write(G[company] + "\n");
                    continue;
                }

                center = Integer.parseInt(st.nextToken());
                company = Integer.parseInt(st.nextToken());

                union(center, company);
            }
        }
        bw.close();
    }

    private static void union(int center, int company) {
        G[center] += Math.abs(center - company) % mod;
        D[center] = company;
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            int pa = find(D[a]);
            G[a] += G[D[a]];
            return D[a] = pa;
        }
    }
}
