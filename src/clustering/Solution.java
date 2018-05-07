package clustering;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Solution {
    private static String str1 = "";
    private static String str2 = "";
    private static int MUL = 65536;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        while (testCase > 0) {
            str1 = sc.next();
            str2 = sc.next();

            findSolution();
            testCase--;
        }
    }

    private static void findSolution() {
        // 다중집합
        String s1 = str1.toUpperCase();
        String s2 = str2.toUpperCase();

        HashMap<String, Integer> map1 = new HashMap<>();
        for (int i = 0; i < s1.length() - 1; i++) {
            char c1 = s1.charAt(i);
            char c2 = s1.charAt(i + 1);

            if (c1 >= 65 && c1 <= 90 && c2 >= 65 && c2 <= 90) {
                String subString = s1.substring(i, i + 2);

                if (map1.containsKey(subString)) {
                    map1.put(subString, map1.get(subString) + 1);
                } else {
                    map1.put(subString, 1);
                }
            }
        }

        HashMap<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s2.length() - 1; i++) {
            char c1 = s2.charAt(i);
            char c2 = s2.charAt(i + 1);

            if (c1 >= 65 && c1 <= 90 && c2 >= 65 && c2 <= 90) {
                String subString = s2.substring(i, i + 2);

                if (map2.containsKey(subString)) {
                    map1.put(subString, map2.get(subString) + 1);
                } else {
                    map2.put(subString, 1);
                }
            }
        }

        HashMap<String, Integer> minMap = new HashMap<>();
        HashMap<String, Integer> maxMap = new HashMap<>();
        // 교집합, 합집합
        for (String key : map1.keySet()) {
            if (minMap.containsKey(key)) {
                if (minMap.get(key) > map1.get(key)) {
                    minMap.put(key, map1.get(key));
                }
            } else {
                minMap.put(key, map1.get(key));
            }

            if (maxMap.containsKey(key)) {
                if (maxMap.get(key) < map1.get(key)) {
                    maxMap.put(key, map1.get(key));
                }
            } else {
                maxMap.put(key, map1.get(key));
            }
        }

        for (String key : map2.keySet()) {
            if (minMap.containsKey(key)) {
                if (minMap.get(key) > map2.get(key)) {
                    minMap.put(key, map2.get(key));
                }
            }

            if (maxMap.containsKey(key)) {
                if (maxMap.get(key) < map2.get(key)) {
                    maxMap.put(key, map2.get(key));
                }
            } else {
                maxMap.put(key, map2.get(key));
            }
        }

        for (String key : minMap.keySet()) {
            if (!map2.containsKey(key)) {
                minMap.remove(key);
            }
        }

        Iterator<String> min = minMap.keySet().iterator();

        Iterator<String> max = maxMap.keySet().iterator();

        int minSize = 0;
        int maxSize = 0;

        while (min.hasNext()) {
            minSize += minMap.get(min.next());
        }
        while (max.hasNext()) {
            maxSize += maxMap.get(max.next());
        }

        int answer;

        if (minSize == 0 && maxSize == 0) answer = MUL;
        else answer = (int) (((double) minSize / (double) maxSize) * MUL);

        System.out.println(answer);
    }
}
