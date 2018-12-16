package data_structure.koitp.lis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[][] trees;

    // koitp.org
    // lis indexed tree
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        trees = new int[N+1][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            trees[i][0] = Integer.parseInt(st.nextToken());
            trees[i][1] = i;
        }

        Arrays.sort(trees, 1, N + 1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
    }
}
