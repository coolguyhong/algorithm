package search.koitp.interview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, M;
    private static int[] T;

    // koitp.org
    // interview : adhoc, parametric search
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        StringBuffer sb = new StringBuffer();
        sb.append(N);
        sb.append(" ");
        sb.append(M);
        sb.append("\n");
        
        T = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            sb.append(T[i] + "\n");
        }

        Arrays.sort(T);

        int temp = M / N;
        long maxTime = T[N-1] * M;
        long minTime = T[0];
        long ans = 0;
        long cnt;
        while (minTime <= maxTime) {
            long mid = (minTime + maxTime) / 2;
            cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += mid / T[i];
            }

            if (cnt >= M) {
                ans = mid;
                maxTime = mid-1;
            } else {
                minTime = mid+1;
            }
        }
        bw.write(sb + "\n");
        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }
}
