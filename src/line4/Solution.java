package line4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static Map m = Collections.synchronizedMap(new LinkedHashMap());
    private static String EVICT = "evict";
    private static String ADD = "add";
    private static String GET = "get";
    private static String REMOVE = "remove";
    private static String EXIT = "exit";
    private static boolean isException = false;

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            String[] input = line.split( " ");
            String order = input[0];
            int key = 0;
            int value = 0;
            if (input.length >= 2) {
                key = Integer.parseInt(input[1]);
            }
            if (input.length == 3) {
                value = Integer.parseInt(input[2]);
            }

            if (order.equals(ADD)) {
                add(key, value);
            } else if (order.equals(GET)) {
                get(key);
            } else if (order.equals(REMOVE)) {
                remove(key);
            } else if (order.equals(EVICT)) {
                evict();
            } else if (order.equals(EXIT)) {
                break;
            }
        }

        if (isException) {
            throw new RuntimeException();
        }
    }

    private static void evict() {
        Iterator<Integer> iterator = m.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.remove();
    }

    private static Map<Integer, Integer> remove(int key) {
        if (!m.containsKey(key)) {
            System.out.println(-1);
            isException = true;
        }

        System.out.println(m.get(key));
        m.remove(key);
        return m;
    }

    private static Map<Integer, Integer> get(int key) {
        if (!m.containsKey(key)) {
            System.out.println(-1);
            isException = true;
            return m;
        }
        int value = Integer.parseInt(m.get(key).toString());
        System.out.println(value);
        m.put(key, value);
        return m;
    }

    private static void add(int key, int value) {
        m.put(key, value);
    }
}