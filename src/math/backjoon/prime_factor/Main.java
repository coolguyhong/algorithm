package math.backjoon.prime_factor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int N, idx;
    private static boolean[] isP = new boolean[10000001];
    private static int[] P = new int[10000001];
    private static final int max = 10000000;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/11653
    // 소인수분해(11653): 소수, 에레스토테네스의 체
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        getPrime();
        setPrime();

        N = Integer.parseInt(br.readLine());
        int n = N;
        for (int i = 0; i < idx; i++) {
            while (n % P[i] == 0) {
                bw.write(P[i] + "\n");
                n /= P[i];
            }

            if (n == 1) {
                break;
            }

            if (i == idx -1) {
                bw.write(n + "\n");
            }
        }
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
