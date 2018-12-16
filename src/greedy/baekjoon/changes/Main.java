package greedy.baekjoon.changes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static final int M = 1000;
    private static int N;
    private static final int[] C = {500, 100, 50, 10, 5, 1};


    // 백준 알고리즘
    // 거스름돈(5585) 탐욕법
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int money = M - N;

        int ans = 0;
        int idx = 0;
        while (money > 0) {
            int change = money / C[idx];
            money = money % C[idx];
            ans += change;
            idx++;
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }
}
