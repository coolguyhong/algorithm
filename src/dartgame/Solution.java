package dartgame;

import java.util.Scanner;

public class Solution {
    private static String game;
    private static int[] score = new int[3];
    private static int result = 0;
    // kakao 1차 2번
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        game = sc.next();

        String[] num = game.split("[SDT](\\#)?(\\*)?");
        String[] option = game.split("\\d+");

        for (int i = 0; i < score.length; i++) {
            score[i] = Integer.parseInt(num[i]);

            if (option[i+1].charAt(0) == 'D') {
                score[i] = score[i] * score[i];
            } else if (option[i+1].charAt(0) == 'T') {
                score[i] = score[i] * score[i] * score[i];
            }

            if (option[i+1].length() == 2) {
                if (option[i+1].charAt(1) == '#') {
                    score[i] = score[i] * -1;
                } else if (option[i+1].charAt(1) == '*') {
                    for (int j = i; j >= 0; j--) {
                        score[j] = score[j] * 2;
                    }
                }
            }
        }
        for (int i = 0; i < score.length; i++) {
            result += score[i];
        }
        System.out.println(result);
    }
}
