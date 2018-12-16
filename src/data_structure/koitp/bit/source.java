package data_structure.koitp.bit;

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
    private static int[] A;
    private static int[] trees;

    // binary indexed tree를 이용하여 i 까지의 presum 구하는 방법
    // 시간복잡도 log n
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        trees = new int[N+1];

        int a;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            A[i] = a;
            update(i, a);
        }

        sum(10);

    }

    private static int sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += trees[i];
            i -= (i & -i);
        }
        return ans;
    }

    private static void update(int i, int a) {
        while (i <= N) {
            trees[i] += a;
            i += (i & -i);
        }
    }
}
