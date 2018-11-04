package lunchbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[] heating;
    private static int[] eating;

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int testCase = input.nextInt();
        while (testCase > 0) {
            n = input.nextInt();
            heating = new int[n];
            eating = new int[n];

            for (int i = 0; i < n; i++) {
                heating[i] = input.nextInt();
            }

            for (int j = 0; j < n; j++) {
                eating[j] = input.nextInt();
            }

            List<LunchBox> boxes = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                LunchBox lunchBox = new LunchBox(heating[k], eating[k]);
                boxes.add(lunchBox);
            }

            Collections.sort(boxes);
            System.out.println(sortFast(boxes));

            testCase--;
        }
    }

    private static int sortFast(List<LunchBox> boxes) {
        int result = 0;
        int totalHeating = 0;
        for (LunchBox lunchBox : boxes) {
            totalHeating += lunchBox.getHeating();
            result = Math.max(result, totalHeating + lunchBox.getEating());
        }

        return result;
    }
}

class LunchBox implements Comparable<LunchBox> {
    private int heating;
    private int eating;

    LunchBox(int heating, int eating) {
        this.heating = heating;
        this.eating = eating;
    }

    public int getHeating() {
        return heating;
    }

    public int getEating() {
        return eating;
    }

    @Override
    public int compareTo(LunchBox o) {
        return o.eating - eating;
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
