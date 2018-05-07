package line4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class myCode {
    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        final LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>();

        for ( ; ; ) {
            final String readLine = bufferedReader.readLine();
            final StringTokenizer stringTokenizer = new StringTokenizer(readLine);
            if (!stringTokenizer.hasMoreTokens()) {
                break;
            }
            final String command = stringTokenizer.nextToken();

            if ("exit".equals(command)) {
                break;
            } else if ("evict".equals(command)) {	// O(1)
                final Iterator<Map.Entry<Integer, Integer>> iterator = cache.entrySet().iterator();
                if (!iterator.hasNext()) {
                    continue;
                }

                iterator.next();
                iterator.remove();
            } else if ("add".equals(command)) {	// 최선: O(1), 최악: O(n)
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                final int key = Integer.parseInt(stringTokenizer.nextToken());
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                final int value = Integer.parseInt(stringTokenizer.nextToken());

                cache.remove(key);
                cache.put(key, value);
            } else if ("get".equals(command)) {	// 최선: O(1), 최악: O(n)
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                final int key = Integer.parseInt(stringTokenizer.nextToken());

                final Integer value = cache.remove(key);
                if (value == null) {
                    System.out.println(-1);
                } else {
                    cache.put(key, value);
                    System.out.println(value.intValue());
                }
            } else if ("remove".equals(command)) {	// 최선: O(1), 최악: O(n)
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                final int key = Integer.parseInt(stringTokenizer.nextToken());

                final Integer value = cache.remove(key);
                if (value == null) {
                    System.out.println(-1);
                } else {
                    System.out.println(value.intValue());
                }
            }
        }

        bufferedReader.close();
    }
}
