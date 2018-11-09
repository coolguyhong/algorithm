package lca;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static int N, Q, K;
    private static int[][] parents;
    private static int[] depth;

    // 최저 공통 조상(LCA)
    public static void main(String[] args) throws IOException {
//        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        InputReader input = new InputReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            Q = input.nextInt();

            int n = N;
            K = 0;
            while (n > 0) {
                K++;
                n /= 2;
            }

            parents = new int[K][N+1];
            depth = new int[N+1];
            depth[1] = 1;

            for (int i = 2; i <= N; i++) {
                parents[0][i] = input.nextInt();
                depth(i);
            }

            fillParents();

            int a, b;
            bw.write("#" + testCase + " ");
            while (Q-- > 0) {
                a = input.nextInt();
                b = input.nextInt();

                bw.write(lca(a, b) + " ");
            }
            bw.newLine();

        }
        bw.flush();
        bw.close();
    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = depth[a] - depth[b];
        int k = 0;
        while (diff > 0) {
            if (diff % 2 == 1) {
                a = parents[k][a];
            }
            k++;
            diff /= 2;
        }

        if (a == b) {
            return a;
        }

        for (k = K-1; K >= 1; k--) {
            if (parents[k][a] == parents[k][b]) {
                continue;
            }

            a = parents[k][a];
            b = parents[k][b];
        }

        return parents[0][a];
    }

    private static void fillParents() {
        for (int k = 1; k < K; k++) {
            for (int i = 1; i <= N; i++) {
                parents[k][i] = parents[k-1][parents[k-1][i]];
            }
        }
    }

    private static int depth(int i) {
        if (depth[i] != 0) {
            return depth[i];
        }

        return depth[i] = depth(parents[0][i]) + 1;
    }
}

class InputReader  {
    private BufferedReader br;
    private StringTokenizer st;

    public InputReader(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream), 32768);
        st = null;
    }

    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}
