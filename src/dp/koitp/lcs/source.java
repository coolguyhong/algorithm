package dp.koitp.lcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static String str1, str2;
    private static char[] A;
    private static char[] B;
    private static int aLength, bLength;
    private static int[][] D;
    private static int[][] path;

    // koitp.org
    // dp.koitp.lcs: DP
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        str1 = st.nextToken();

        st = new StringTokenizer(br.readLine());
        str2 = st.nextToken();

        aLength = str1.length();
        bLength = str2.length();
        A = new char[aLength+1];
        B = new char[bLength+1];
        D = new int[aLength+1][bLength+1];
        path = new int[aLength+1][bLength+1];

        for (int i = 1; i <= aLength; i++) {
            A[i] = str1.charAt(i-1);
        }
        for (int i = 1; i <= bLength; i++) {
            B[i] = str2.charAt(i-1);
        }

        lcs();
        int a = aLength;
        int b = bLength;
        StringBuffer sb = new StringBuffer();
        while (path[a][b] != 0) {
            if (path[a][b] == 3) {
                sb.append(A[a]);
                a--;
                b--;
            } else if (path[a][b] == 2) {
                a--;
            } else if (path[a][b] == 1) {
                b--;
            }
        }
        bw.write(sb.reverse().toString() + "\n");
        bw.flush();
        bw.close();
    }

    private static void lcs() {
        for (int i = 0; i <= aLength; i++) {
            D[i][0] = 0;
            for (int j = 1; j <= bLength; j++) {
                if (i == 0) {
                    D[i][j] = 0;
                    continue;
                }

                if (A[i] == B[j]) {
                    D[i][j] = D[i-1][j-1]+1;
                    path[i][j] = 3;
                } else {
                    D[i][j] = max(D[i-1][j], D[i][j-1]);
                    if (D[i][j] == D[i-1][j]) {
                        path[i][j] = 2;
                    } else {
                        path[i][j] = 1;
                    }
                }
            }
        }
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
