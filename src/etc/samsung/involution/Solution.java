package etc.samsung.involution;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private static long A, M;
    private static long mod = 1000000007;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(st.nextToken());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            M = Long.parseLong(st.nextToken());

            long ans = calculate(A, M);

            bw.write("#" + testCase + " " + ans + "\n");
            bw.flush();

        }
        bw.close();
    }

    private static long calculate(long a, long m) {
        long ans = 1;
        long a2k = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans *= a2k;
                ans %= mod;
            }

            m /= 2;
            a2k *= a2k;
            a2k %= mod;
        }
        return ans;
    }
}
