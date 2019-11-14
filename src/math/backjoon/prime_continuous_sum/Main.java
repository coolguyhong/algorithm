package math.backjoon.prime_continuous_sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int N, idx;
    private static boolean[] isP = new boolean[4000001];
    private static int[] P = new int[4000001];
    private static final int max = 4000000;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/1644
    // 소수의 연속합(1644): 에레스토테니스의 체, 소수, 정수론
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        getPrime();
        setPrime();

        N = Integer.parseInt(br.readLine());

        int cnt = 0;
        int sum = 0;
        int right = 0;
        for (int i = 0; i < idx; i++) {
            while (right < idx && sum < N) {
                sum += P[right++];
            }

            if (sum == N) {
                cnt++;
            }

            sum -= P[i];
        }

        bw.write(cnt + "\n");
        bw.close();
    }

    private static void setPrime() {
        for (int i = 2; i <= max; i++) {
            if (isP[i]) {
                P[idx++] = i;
            }
        }
    }

    private static void getPrime() {
        for (int i = 2; i <= max; i++) {
            isP[i] = true;
        }

        int sqrt = (int) Math.sqrt(max);
        for (int i = 2; i <= sqrt; i++) {
            if (!isP[i]) {
                continue;
            }

            for (int j = i + i; j <= max; j += i) {
                if (!isP[j]) {
                    continue;
                }

                isP[j] = false;
            }
        }
    }
}