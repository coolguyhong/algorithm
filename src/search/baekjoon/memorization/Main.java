package search.baekjoon.memorization;

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

    private static int N, M;
    private static int[] A;


    // 백준 알고리즘
    // 암기왕(2776): 이분탐색
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A, 1, N+1);

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int x;
            for (int i = 0; i < M; i++) {
                x = Integer.parseInt(st.nextToken());

                if (binarySearch(x)) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            }
        }

        bw.flush();
        bw.close();
    }

    private static boolean binarySearch(int x) {
        int start = 1;
        int end = N;

        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (A[mid] > x) {
                end = mid - 1;
            } else if (A[mid] < x) {
                start = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
