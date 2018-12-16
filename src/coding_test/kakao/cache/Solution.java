package coding_test.kakao.cache;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    private static int cacheSize;
    private static int cityNum;
    private static String[] cities;
    private static int HIT = 1;
    private static int MISS = 5;

    // kakao 1차 3번
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        while (testCase > 0) {
            cacheSize = sc.nextInt();
            cityNum = sc.nextInt();
            cities = new String[cityNum];
            for (int i = 0; i < cityNum; i++) {
                cities[i] = sc.next();
            }

            LRU();

            testCase--;
        }
    }

    private static void LRU() {
        Queue<String> queue = new LinkedList<>();
        int answer = 0;
        for (String city : cities) {
            String c = city.toLowerCase();
            if (queue.contains(c)) {
                queue.remove(c);
                queue.add(c);
                answer += HIT;
                continue;
            }

            if (queue.size() >= cacheSize) {
                queue.poll();
            }
            queue.add(c);

            answer += MISS;
        }

        System.out.println(answer);
    }
}
