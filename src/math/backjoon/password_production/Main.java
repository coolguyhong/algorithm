package math.backjoon.password_production;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static String P;
    private static int K, idx;
    private static boolean[] isP = new boolean[1000001];
    private static int[] p = new int[1000001];

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/1837
    // 암호제작(1837): 수학, 소수
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        P = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        getPrime(K);

        int prime = 0;
        for (int i = 0; i < idx; i++) {
            if (div(p[i])) {
                prime = p[i];
                break;
            }
        }

        if (prime > 0) {
            bw.write("BAD" + " " + prime + "\n");
        } else {
            bw.write("GOOD");
        }
        bw.close();
    }

    private static boolean div(int n) {
        int d = 0;
        int len = P.length();
        for (int i = 0; i < len; i++) {
            d = d * 10 + P.charAt(i) - '0';
            if (d >= n) {
                d %= n;
            }
        }

        return d == 0;
    }

    private static void getPrime(int k) {
        for (int i = 2; i < k; i++) {
            isP[i] = true;
        }

        for (int i = 2; i < k; i++) {
            if (!isP[i]) {
                continue;
            }

            p[idx++] = i;

            for (int j = i + i; j < k; j += i) {
                if (!isP[j]) {
                    continue;
                }

                isP[j] = false;
            }
        }
    }
}
