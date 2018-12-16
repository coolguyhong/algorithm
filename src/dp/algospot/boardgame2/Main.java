package dp.algospot.boardgame2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int N, K;
    private static int[] M = new int[7];
    private static int[] PRICE = new int[201];
    private static int[] cache = new int[201];

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int testCase = input.nextInt();
        for (int i = 1; i <= testCase; testCase++) {
            N = input.nextInt();
            K = input.nextInt();
            M[0] = input.nextInt();

            // 각 카드별 사용 가능 횟수
            for (int k = 1; k <= K; k++) {
                M[k] = M[0];
            }

            // 각 위치별 통행료 할당
            for (int j = 1; j <= N; j++) {
                cache[j] = -1;
                PRICE[j] = input.nextInt();
            }
            
            int result = minimumPrice(1, 1, 0);
            System.out.println("#" + i + " " + result);

        }
    }

    private static int minimumPrice(int here, int distance, int price) {
        // 한 바퀴 돌았을 경우 한 바퀴 도착한 후 도착한 곳의 비용 더해줌
        if (here > N) {
            return cache[here] + PRICE[here-N];
        }

        if (cache[here] == -1) {
            cache[here] = price;
        }

        for (int i = 1; i <= K; i++) {
            if (i == distance || M[i] == 0) {
                continue;
            }
            M[i]--;
            int tmpPrice = minimumPrice(here + i, i, price);
            if (cache[here] >= tmpPrice) {
                cache[here] = tmpPrice;
            }
        }
        return cache[here];
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
