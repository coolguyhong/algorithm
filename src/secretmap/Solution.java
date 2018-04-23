package secretmap;

import java.util.Scanner;

public class Solution {
    private static int N;
    private static int[] arr1;
    private static int[] arr2;
    private static String[] str1;
    private static String[] str2;
    private static String[] map;

    // kakao 1차 1번
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr1 = new int[N];
        arr2 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int j = 0; j < N; j++) {
            arr2[j] = sc.nextInt();
        }

        changeToBinary(arr1, arr2);
    }

    private static void changeToBinary(int[] arr1, int[] arr2) {
        str1 = new String[N];
        str2 = new String[N];
        for (int i = 0; i < N; i++) {
            str1[i] = Integer.toBinaryString(arr1[i]);
            str2[i] = Integer.toBinaryString(arr2[i]);
        }
        addZero(str1);
        addZero(str2);

        showMap(str1, str2);
    }

    private static void showMap(String[] str1, String[] str2) {
        map = new String[N];
        String rowmap;
        for (int i = 0; i < N; i++) {
            rowmap = "";
            for (int j = 0; j < N; j++) {
                if (str1[i].charAt(j) == '1' || str2[i].charAt(j) == '1') {
                    rowmap = rowmap + "#";
                    continue;
                }
                rowmap = rowmap + " ";
            }
            map[i] = rowmap;
            System.out.println(map[i]);
        }
    }

    private static void addZero(String[] str) {
        for (int i = 0; i < N; i++) {
            if (str[i].length() == N) {
                continue;
            }

            while (str[i].length() < N) {
                str[i] = "0" + str[i];
            }
        }
    }
}
