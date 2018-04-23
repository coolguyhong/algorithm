package dartgame;

public class Solution1 {
    public static void main(String[] args) {

        String[] testCase = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*"};
        for (String s : testCase) {

            System.out.println(solution(s));
        }
    }

    public static int solution(String dartResult) {

        int[] score = new int[3];
        String[] num = dartResult.split("[SDT](\\#)?(\\*)?");
        String[] operator = dartResult.split("\\d+");

        for (int i = 0; i < 3; i++) {

            char op1 = operator[i + 1].charAt(0);

            // S, D, T
            switch (op1) {

                case 'S':
                    score[i] = Integer.parseInt(num[i]);
                    break;

                case 'D':
                    score[i] = Integer.parseInt(num[i]);
                    score[i] *= score[i];
                    break;

                case 'T':
                    score[i] = Integer.parseInt(num[i]);
                    score[i] *= score[i] * score[i];
                    break;
            }

            // *, #
            if (operator[i + 1].length() > 1) {

                char op2 = operator[i + 1].charAt(1);

                switch (op2) {

                    case '*':
                        for (int j = (i - 1 < 0 ? 0 : i - 1); j <= i; j++) {

                            score[j] *= 2;
                        }
                        break;

                    case '#':
                        score[i] *= -1;
                        break;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {

            answer += score[i];
        }
        return answer;
    }
}
