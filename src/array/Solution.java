package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    private static int[] item;
    private static int[] array;
    private static int number;
    private static Set<Integer> arrayIndex = new HashSet<>();

    public static void main(String[] args) {
        InputReader inputReader = new InputReader(System.in);
        int testCase = inputReader.nextInt();
        for (int i = 1; i <= testCase; i++) {
            array = new int[inputReader.nextInt()];
            number = inputReader.nextInt();
            item = new int[number];
            for (int j = 0; j < number; j++) {
                item[j] = inputReader.nextInt();
            }

            countEmptyArray(0, 0);
            System.out.println("#" + i + " " + arrayIndex.size());
            arrayIndex.clear();
        }

    }

    private static void countEmptyArray(int index, int lastIndex) {
        if (index + lastIndex >= array.length) {
            return;
        }

        for (int i = 0; i < number; i++) {
            if (array[index + lastIndex] == 0) {
                array[index + lastIndex]++;
                arrayIndex.add(index + lastIndex);
                countEmptyArray(lastIndex, item[i]);
            }
        }
    }

    static class InputReader {
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
}
