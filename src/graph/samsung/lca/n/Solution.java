package graph.samsung.lca.n;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static int N, Q;
    private static int[] parents;
    private static int[] depth;

    // 최저 공통 조상(LCA) time out
    public static void main(String[] args) throws IOException {
//        InputReader input = new InputReader(System.in);
        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            Q = input.nextInt();

            parents = new int[N+1];
            depth = new int[N+1];
            depth[1] = 1;

            for (int i = 2; i <= N; i++) {
                parents[i] = input.nextInt();
                depth(i);
            }

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

        while (depth[a] > depth[b]) {
            a = parents[a];
        }

        if (a == b) {
            return a;
        }

        while (a != b) {
            a = parents[a];
            b = parents[b];
        }

        return a;
    }

    private static int depth(int i) {
        if (depth[i] != 0) {
            return depth[i];
        }

        return depth[i] = depth(parents[i]) + 1;
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
