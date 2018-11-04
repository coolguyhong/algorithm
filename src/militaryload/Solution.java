package militaryload;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

    private static int N, M, K;
    private static int[] D;
    private static int[][] G;
    private static long ans;

    public static void main(String[] args) throws IOException {
        InputReader input = new InputReader(new FileInputStream(Solution.class.getResource("sample_input.txt").getPath()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            M = input.nextInt();
            K = input.nextInt();

            D = new int[N+1];
            for (int i = 1; i <= N; i++) {
                D[i] = i;
            }

            G = new int[M+K][3];
            int a, b, c;
            ans = 0;
            for (int i = 0; i < M+K; i++) {
                a = input.nextInt();
                b = input.nextInt();
                c = input.nextInt();

                G[i][0] = a;
                G[i][1] = b;
                if (i < M) {
                    G[i][2] = -c;
                    ans += c;
                } else {
                    G[i][2] = c;
                }
            }

            kruskal();

            bw.write("#" + testCase + " " + ans + "\n");
            bw.flush();
        }
        bw.close();
    }

    private static void kruskal() {
        Arrays.sort(G, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        int a, b;
        for (int i = 0; i < M+K; i++) {
            a = G[i][0];
            b = G[i][1];

            if (isUnion(a, b)) {
                continue;
            } else {
                union(a, b);
                ans += G[i][2];
            }
        }
    }

    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        D[pa] = pb;
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
