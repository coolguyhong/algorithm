package dp.baekjoon.coin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n, k;
    private static int[] coin, D;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/2294
    // 동전2: DP
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        D = new int[k + 1];
        Arrays.fill(D, 10001);

        D[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                D[j] = Math.min(D[j], D[j - coin[i]] + 1);
            }
        }

        bw.write((D[k] == 10001 ? -1 : D[k]) + "\n");
        bw.close();
    }
}
