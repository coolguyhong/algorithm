package lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Main2 {
    private static int[] SEQUENCE = new int[500];
    private static int[] cache = new int[500];
    private static int Lengh_Sequence;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int testCase = input.nextInt();
        while (testCase > 0) {
            Lengh_Sequence = input.nextInt();
            for (int i = 0; i < Lengh_Sequence; i++) {
                cache[i] = -1;
                SEQUENCE[i] = input.nextInt();
            }
            System.out.println(lis(0));
            testCase--;
        }
    }

    private static int lis(int location) {
        if (cache[location] != -1) {
            return cache[location];
        } else {
            cache[location] = 1;
        }

        for (int next = location + 1; next < Lengh_Sequence; next++) {
            if (SEQUENCE[location] < SEQUENCE[next]) {
                return cache[location] = max(cache[location], lis(next) + 1);
            }
        }
        return cache[location];
    }

    static class InputReader {
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
}
