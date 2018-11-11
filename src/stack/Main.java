package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static final String PUSH = "push";
	private static final String TOP = "top";
	private static final String SIZE = "size";
	private static final String EMPTY = "empty";
	private static final String POP = "pop";
	private static Deque<Integer> dq = new ArrayDeque<>();
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		String str;
		int num;
		for (int i = 0; i < N; i++) {
			str = input.next();
			
			if (str.equals(PUSH)) {
				num = input.nextInt();
				dq.add(num);
			} else if (str.equals(TOP)) {
				if (dq.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(dq.peekLast());
				}
			} else if (str.equals(POP)) {
				if (dq.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(dq.pollLast());
				}
			} else if (str.equals(SIZE)) {
				System.out.println(dq.size());
			} else if (str.equals(EMPTY)) {
				if (dq.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			}
		}

	}

}

class InputReader {
	public BufferedReader br;
	public StringTokenizer st;
	
	public InputReader(InputStream stream) {
		br = new BufferedReader(new InputStreamReader(stream), 32768);
		st = null;
	}
	
	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}
	
	public int nextInt() {
		return Integer.parseInt(next());
	}
}
