package pi;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static String PI;
    private static int[] cache = new int[10000];
    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int testCase = input.nextInt();
        while (testCase > 0) {
            PI = input.next();

            System.out.println(memorize());

            testCase--;
        }
    }

    private static int memorize() {
//        if (cache[])
        return 0;
    }
}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

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
