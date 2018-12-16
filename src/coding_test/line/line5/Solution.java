package coding_test.line.line5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Solution {
    private static boolean isException = false;
    private static PriorityQueue<Integer> queue = new PriorityQueue<>();
    private static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for ( ; ; ) {
            final String readLine = br.readLine();
            final StringTokenizer stringTokenizer = new StringTokenizer(readLine);
            if (!stringTokenizer.hasMoreTokens()) {
                break;
            }

            final String command = stringTokenizer.nextToken();

            if ("exit".equals(command)) {
                break;
            } else if ("add".equals(command)) {
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }

                final int value = Integer.parseInt(stringTokenizer.nextToken());
                add(value);
            } else if ("min".equals(command)) {
                min();
            } else if ("remove".equals(command)) {
                remove();
            }
        }

        if (isException) {
            throw new RuntimeException();
        }

        br.close();
    }

    private static void min() {
        Iterator<Integer> iterator = queue.iterator();
        if (!iterator.hasNext()) {
            isException = true;
            System.out.println(-1);
        } else {
            System.out.println(iterator.next());
        }
    }

    private static void add(int value) {
        queue.add(value);
        list.add(value);
    }

    private static void remove() {
        Iterator<Integer> iterator = list.iterator();
        if (!iterator.hasNext()) {
            isException = true;
            System.out.println(-1);
        } else {
            final Integer value = iterator.next();

            iterator.remove();
            queue.remove(value);

            System.out.println(value);
        }
    }
}