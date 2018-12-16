package dp.koitp.fibonacci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static long[] D;

    // koitp.org
    // dp FIBONACCI_1
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = new long[N+1];

        fibonacci(N);
        bw.write(D[N] + "\n");
        bw.flush();
        bw.close();
    }

    private static long fibonacci(int n) {
        if (D[n] != 0) {
            return D[n];
        }

        if (n < 2) {
            return D[n] = n;
        } else {
            return D[n] = fibonacci(n-1) + fibonacci(n-2);
        }
    }
}
