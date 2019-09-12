package data_structure.samsung.barcode;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static String str;
    private static String rerverseStr;
    private static String ACODE;
    private static int L;
    private static final String[] C = {"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};
    private static final String[] BstrucByA = {"LLLLLL", "LLGLGG", "LLGGLG", "LLGGGL", "LGLLGG", "LGGLLG", "LGGGLL", "LGLGLG", "LGLGGL", "LGGLGL"};
    private static final String[][] B = {{"0001101", "0100111"}, {"0011001", "0110011"}, {"0010011", "0011011"}, {"0111101", "0100001"}, {"0100011", "0011101"}, {"0110001", "0111001"}, {"0101111", "0000101"}, {"0111011", "0010001"}, {"0110111", "0001001"}, {"0001011", "0010111"}};
    private static int[] code;
    private static List<String> strList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        for (int T = 1; T <= testCase; T++) {
            st = new StringTokenizer(br.readLine());
            str = st.nextToken();
            L = str.length();
            rerverseStr = new StringBuilder(str).reverse().toString();

            int end = L - 95;
            code = new int[14];
            Arrays.fill(code, -1);
            int ans = 0;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i <= end;) {
                if (!"101".equals(str.substring(i, i+3)) || !"01010".equals(str.substring(i+45, i+50)) || !"101".equals(str.substring(i+92, i+95))) {
                    i++;
                    continue;
                }

                strList = new ArrayList<>();
                extractCode(str);

                if (!isValidB()) {
                    i++;
                    continue;
                }

                if (!isValidA()) {
                    i++;
                    continue;
                }

                if (!isValidC()) {
                    i++;
                    continue;
                }

                if (!isValid()) {
                    i++;
                    continue;
                }

                ans++;
                for (int j = 1; j <= 13; j++) {
                    sb.append(code[j]);
                }
                sb.append(" ");
                i += 2;
            }

            Arrays.fill(code, -1);
            for (int i = 0; i <= end;) {
                if (!"101".equals(rerverseStr.substring(i, i+3)) || !"01010".equals(rerverseStr.substring(i+45, i+50)) || !"101".equals(rerverseStr.substring(i+92, i+95))) {
                    i++;
                    continue;
                }

                strList = new ArrayList<>();
                extractCode(rerverseStr);

                if (!isValidB()) {
                    i++;
                    continue;
                }

                if (!isValidA()) {
                    i++;
                    continue;
                }

                if (!isValidC()) {
                    i++;
                    continue;
                }

                if (!isValid()) {
                    i++;
                    continue;
                }

                ans++;
                for (int j = 1; j <= 13; j++) {
                    sb.append(code[j]);
                }
                sb.append(" ");
                i += 2;
            }

            bw.write("#" + T + " " + ans + " " + sb + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void extractCode(String param) {
        for (int j = 10; j <= 45 ; j+=7) {
            strList.add(param.substring(j-7, j));
        }

        for (int j = 57; j <= 92 ; j+=7) {
            strList.add(param.substring(j-7, j));
        }
    }

    private static boolean isValidC() {
        boolean isValid = true;
        // groupC code 찾기
        for (int j = 8; j <= 13; j++) {
            for (int k = 0; k < 10; k++) {
                if (C[k].equals(strList.get(j-2))) {
                    code[j] = k;
                    break;
                }
            }

            if (code[j] == -1) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    private static boolean isValidA() {
        boolean isValid = false;
        for (int j = 0; j < 10; j++) {
            if (BstrucByA[j].equals(ACODE)) {
                code[1] = j;
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private static boolean isValidB() {
        StringBuffer a = new StringBuffer();
        for (int j = 2; j <= 7; j++) {
            loop: for (int k = 0; k < 10; k++) {
                for (int l = 0; l < 2; l++) {
                    if (B[k][l].equals(strList.get(j-2))) {
                        code[j] = k;
                        if (l == 0) {
                            a.append("L");
                        } else {
                            a.append("G");
                        }

                        break loop;
                    }
                }
            }

            if (code[j] == -1) {
                break;
            }
        }

        ACODE = a.toString();
        if (ACODE.length() != 6) {
            return false;
        }

        return true;
    }

    private static boolean isValid() {
        int odd = 0;
        int even = 0;
        for (int i = 1; i <= 12; i++) {
            if (i % 2 == 0) { // 짝
                even += code[i];
            } else { // 홀
                odd += code[i];
            }
        }

        int a = odd + even * 3;
        a %= 10;

        if (a == 0) {
            if (code[13] == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (10 - a == code[13]) {
                return true;
            } else {
                return false;
            }
        }
    }
}
