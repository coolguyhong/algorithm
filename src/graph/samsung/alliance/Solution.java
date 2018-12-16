package graph.samsung.alliance;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static int N, Q;
    private static int[] D;

    // 사내 프로 문제풀이
    // 동맹: union find
    public static void main(String[] args) throws IOException {
        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            Q = input.nextInt();

            D = new int[N+1];
            for (int i = 1; i <= N; i++) {
                D[i] = i;
            }

            int q, a, b;
            int ans = 0;
            for (int i = 0; i < Q; i++) {
                q = input.nextInt();
                a = input.nextInt();
                b = input.nextInt();
                if (q == 0) {
                    union(a, b);
                } else if (isUnion(a, b)) {
                    ans++;
                }
            }

            bw.write("#" + testCase + " " + ans + "\n");
            bw.flush();

        }
        bw.close();
    }

    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }

    private static void union(int a, int b) {
        D[find(a)] = find(b);
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }
}

class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}