package christmas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K;
    private static double[][] p;
    // 사내 알고리즘 문제
    // 크리스마스 선물: 프로 시험 기출문제
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            p = new double[N][3];
            double a, b;
            double sumA = 0.0;
            double sumB = 0.0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                a = Double.parseDouble(st.nextToken());
                b = Double.parseDouble(st.nextToken());

                p[i][0] = a;
                p[i][1] = b;

                sumA += a;
                sumB += b;
            }
            double min = sumA / sumB;
            double max = 100;
            double ans = 0;
            double mid;
            while (min < max) {
                mid = (min + max) / 2;
                sortByC(min);
                sumA = 0;
                sumB = 0;
                for (int i = 0; i < K; i++) {
                    sumA += p[i][0];
                    sumB += p[i][1];
                }

                if (sumA - (mid * sumB) >= 0) {
                    ans = mid;
                    min = mid + 0.001;
                } else {
                    max = mid;
                }
            }
            bw.write("#" + testCase + " " + String.format("%.4g%n", ans) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void sortByC(double c) {
        for (int i = 0; i < N; i++) {
            p[i][2] = p[i][0] - (c * p[i][1]);
        }

        Arrays.sort(p, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o2[2], o1[2]);
            }
        });
    }
}
