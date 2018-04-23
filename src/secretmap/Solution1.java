package secretmap;

import java.util.Scanner;

public class Solution1 {
    private static int N;
    private static int[] arr1;
    private static int[] arr2;
    private static String[] str;

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

        calculateBinary(arr1, arr2);
    }

    private static void calculateBinary(int[] arr1, int[] arr2) {
        str = new String[N];
        int binary;
        for (int i = 0; i < N; i++) {
            binary = arr1[i] | arr2[i];
            str[i] = Integer.toBinaryString(binary);
            for (int j = 0; j < N; j++) {
                if (str[i].charAt(j) == '1') {
                    System.out.print("#");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
