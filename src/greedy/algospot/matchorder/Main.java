package greedy.algospot.matchorder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] RUSSIA;
    private static List<Integer> koreaList = new ArrayList<>();
    private static int C;
    private static int winning = 0;


    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int testCase = input.nextInt();
        for (int i = 1; i <= testCase; i++) {
            C = input.nextInt();
            RUSSIA = new int[C];

            for (int j = 0; j < C; j++) {
                RUSSIA[j] = input.nextInt();
            }

            for (int j = 0; j < C; j++) {
                koreaList.add(input.nextInt());
            }
            Collections.sort(koreaList);

            for (int k = 0; k < C; k++) {
                sortKorea(RUSSIA[k]);
            }

            System.out.println(winning);
            winning = 0;
        }

    }

    private static void sortKorea(int russia) {
        int temp = 0;
        for (int i = 0; i < koreaList.size(); i++) {
            if (koreaList.get(i) >= russia) {
                winning++;
                koreaList.remove(i);
                temp++;
                break;
            }
        }

        if (temp == 0) {
            koreaList.remove(0);
        }
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
