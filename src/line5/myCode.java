package line5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class myCode {
    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        final PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        final LinkedList<Integer> list = new LinkedList<Integer>();

        for ( ; ; ) {
            final String readLine = bufferedReader.readLine();
            final StringTokenizer stringTokenizer = new StringTokenizer(readLine);
            if (!stringTokenizer.hasMoreTokens()) {
                break;
            }
            final String command = stringTokenizer.nextToken();

            if ("exit".equals(command)) {
                break;
            }
            else if ("min".equals(command)) {	// 최선: O(1), 최악: O(n)
                final Iterator<Integer> iterator = queue.iterator();
                if (iterator.hasNext()) {
                    System.out.println(iterator.next());
                } else {
                    System.out.println(-1);
                }
            }
            else if ("add".equals(command)) {
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                final int value = Integer.parseInt(stringTokenizer.nextToken());

                queue.add(value);
                list.add(value);
            }
            else if ("remove".equals(command)) {	// 최선: O(1), 최악: O(n)
                final Iterator<Integer> iterator = list.iterator();
                if (iterator.hasNext()) {
                    final Integer value = iterator.next();

                    iterator.remove();
                    queue.remove(value);

                    System.out.println(value);
                } else {
                    System.out.println(-1);
                }
            }
        }

        bufferedReader.close();
    }
}
