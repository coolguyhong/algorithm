package math.backjoon.candy_divide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static long K, C;
    private static final long mod = 1000000000L;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/3955
    // 캔디 분배(3955): 확장유클리드 호제법, 최대공약수
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = Long.parseLong(st.nextToken());
            C = Long.parseLong(st.nextToken());

            XY ans = extendedEuclidean(K, C);
            if (ans == null) {
                bw.write("IMPOSSIBLE\n");
            } else {
                bw.write(ans.y + "\n");
            }
        }
        bw.close();
    }

    private static XY extendedEuclidean(long k, long c) {
        // 선형 다오판토스 방정식에 의하여 일차부정방정식 ax + by = c이고 d = gcd(a, b)라 할 때
        // c / d 즉 c를 d로 나누었을 때 나누어떨어지지 않는다면 해가 존재하지 않으며,
        // 그렇지 않다면 해가 무수히 존재하며 이 수식을 만족하는 하나의 특수 해를 구한다면 일반 해를 구해낼 수 있다.
        // K*x + 1 = Cy;
        // K*(-x) + C*y = 1;
        // c는 1이므로 해를 갖기 위해서는 gcd(C, K) = 1이여야함
        long gcd = gcd(k, c);
        if (1 % gcd != 0) {
            return null;
        }

        // http://joonas.tistory.com/25
        // 확장 유클리드 호제법 설명
        long s0, t0, r0;
        long s1, t1, r1;
        long s, t, r, q;

        // 초기조건
        s0 = 1; s1 = 0; t0 = 0; t1 = 1; r0 = k; r1 = c;

        s = s1; t = t1; r = r1;
        while (r > gcd) {
            r = r0 % r1;
            q = r0 / r1;
            s = s0 - (q * s1);
            t = t0 - (q * t1);

            s0 = s1; t0 = t1; r0 = r1;
            s1 = s; t1 = t; r1 = r;
        }

        while (s >= 0 || t <= 0) {
            s -= c;
            t += k;
        }

        if (t > mod) {
            return null;
        }

        return new XY(s, t);
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }

    }
}

class XY {
    long x, y;

    XY(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
