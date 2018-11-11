package termsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, K;
	private static int[] sum;
	private static int[] num;
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		M = input.nextInt();
		K = input.nextInt();
		
		sum = new int[N+1];
		num = new int[N+1];
		sum[0] = 0;
		for (int i = 1; i <= N; i++) {
			num[i] = input.nextInt();
			sum[i] = sum[i-1] + num[i];
		}
		
		int a, b, c;
		for (int i = 0; i < M+K; i++) {
			a = input.nextInt();
			b = input.nextInt();
			c = input.nextInt();
			
			if (a == 1) {
				change(b, c);
			} else {
				System.out.println(sum[c] - sum[b-1]);
			}
		}
	}

	private static void change(int b, int c) {
		sum[b] = sum[b-1] + c;
		for (int i = b+1; i <= N; i++) {
			sum[i] = sum[i-1] + num[i];
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}
	
	public int nextInt() {
		return Integer.parseInt(next());
	}
}
