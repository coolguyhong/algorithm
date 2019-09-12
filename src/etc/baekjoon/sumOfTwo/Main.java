package etc.baekjoon.sumOfTwo;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int sum = 0;
        int right = 0;
        for (int i = 1; i <= N; i++) {
            while (right < N && sum < M) {
                sum += A[++right];
            }

            if (sum == M) {
                ans++;
            }

            sum -= A[i];
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}
