package dp.samsung.combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	private static int Q;
	private static long[][] com = new long[5001][5001];
	private static final int mod = 1000000007;
	
	public static void main(String[] args) throws IOException {
		InputReader input = new InputReader(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		com[0][0] = 1;
		for (int i = 1; i <= 5000; i++) {
			for (int j = 0; j <= 5000; j++) {
				com[i][j] = (com[i-1][j] + (j > 0 ? com[i-1][j-1] : 0)) % mod;
			}
		}
		Q = input.nextInt();
		for (int testCase = 1; testCase <= Q; testCase++) {
			int a = input.nextInt();
			int b = input.nextInt();
			
			bw.write("#" + testCase + " " + com[a][b] + "\n");
		}
		bw.flush();
		bw.close();
		
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