package kmp;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static String string;

    public static void main(String[] args) throws IOException {
        InputReader input = new InputReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = input.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = input.nextInt();
            string = input.next();

            int[] result = preProcessPattern(string.toCharArray());
            int ans = 0;
            for (int i : result) {
                ans += i;
            }

            bw.write("#" + testCase + " " + ans + "\n");
            bw.flush();

//            for (int k = 1; k <= N-1; k++) {
//                for (int i = 0; i < N; i++) {
//                    if (i+k >= N) {
//                        break;
//                    }
//                    String pattern = string.substring(i, i+k);
//
//                }
//            }

        }
        bw.close();
    }

    private static int[] preProcessPattern(char[] ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length;
        int[] b = new int[ptrnLen + 1];

        b[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn[i] != ptrn[j]) {
                // if there is mismatch consider the next widest border
                // The borders to be examined are obtained in decreasing order from
                //  the values b[i], b[b[i]] etc.
                j = b[j];
            }
            i++;
            j++;
            b[i] = j;
        }
        return b;
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
