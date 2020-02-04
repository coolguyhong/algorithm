package dp.algospot.pi;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static String str;
    private static char[] c;
    private static int[] D; // i 자리에서 얻을 수 있는 최소 값

    // 알고스팟 문제해결전략(원주율 외우기)
    // https://www.algospot.com/judge/problem/read/PI
    // dp, 원주율 외우기
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine().trim());
        while (C-- > 0) {
            str = br.readLine().trim();
            c = str.toCharArray();
            D = new int[str.length()+1];
            Arrays.fill(D, Integer.MAX_VALUE);

            lowest();

            bw.write(D[str.length()] + "\n");
        }
        bw.close();
    }

    private static void lowest() {
        // 초기 값
        D[3] = getLowestScore(0, 3);
        D[4] = getLowestScore(0, 4);
        D[5] = getLowestScore(0, 5);

        for (int i = 3; i <= str.length() - 3; i++) {
            if (i + 3 <= str.length()) {
                D[i+3] = Math.min(D[i+3], getLowestScore(i, 3) + D[i]);
            }
            if (i + 4 <= str.length()) {
                D[i+4] = Math.min(D[i+4], getLowestScore(i, 4) + D[i]);
            }
            if (i + 5 <= str.length()) {
                D[i+4] = Math.min(D[i+5], getLowestScore(i, 5) + D[i]);
            }
        }
    }

    private static int getLowestScore(int s, int len) {
        int isSame = 1;
        int gap = (c[s+1] - '0') - (c[s] - '0');

        for (int i = s+1; i < s + len - 1; i++) {
            int a = (c[i+1] - '0');
            int b = (c[i] - '0');
            if ((a - b) != gap) {
                isSame = 0;
                break;
            }
        }

        if (isSame == 1 && gap == 0) { // 숫자가 같을 경우
            return 1;
        } else if (isSame == 1 && Math.abs(gap) == 1) { // 숫자가 단조 1씩 증가 또는 1씩 감소
            return 2;
        }

        int isCross = 1;
        for (int i = s + 2; i <= s + len - 1; i++) {
            if (i % 2 == s % 2 && c[s] != c[i]) {
                isCross = 0;
                break;
            } else if (i % 2 == (s + 1) % 2 && c[s+1] != c[i]) {
                isCross = 0;
                break;
            }
        }

        if (isCross == 1) {
            return 4;
        } else if (isSame == 1 && Math.abs(gap) > 1) {
            return 5;
        } else {
            return 10;
        }
    }
}

