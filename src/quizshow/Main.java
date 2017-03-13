package quizshow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
//N���� �л� 2<=N<=1000
//N���� ����
//i��° ������ i�� �л����� Ǯ �� ����
//i�� �л��� Ʋ���� �� ���� i+1�� �л��� Ǯ�� ���� N��° �л����� �� ��Ǯ�� �� ó�� �л����� �ٽ� ������
//�� �л��� ������ Ǯ �� �ִ� ������ ���� ���� ����, �����, ����
//�� ������ �� ������ Ǯ �� �ִ� ����ġ�� ���� ����
//�� �л��� �� ������ Ǯ �� ����ġ�� ������ ������ Ǯ �� �ְ�, ���� �ʾƵ� �� ���� �� ����
//�� ������ �������̰� K���� ���⸦ ���� ����
//�л����� �ȶ��ؼ� �� ������ Ʋ���� �������̱� ������ �ջ���� ������ ���� �������� ����
//�л����� ������ ���߷��� �ּ��� ����
	
//A�л��� �ִ������� �ּ������� ���ض�
	
	static int N, K, A;
	static int[][] N_spec = new int[1000][3];
	static int[][] N_prob = new int[1000][4];
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int test_case = input.nextInt();
		
		for ( int i = 1 ; i <= test_case ; i++ ) {
			N = input.nextInt();
			K = input.nextInt();
			A = input.nextInt();
			
			for ( int j = 0 ; j < N ; j++ ) {
				N_spec[j][0] = input.nextInt();
				N_spec[j][1] = input.nextInt();
				N_spec[j][2] = input.nextInt();
			}

			for ( int k = 0 ; k < N ; k++ ) {
				N_prob[k][0] = input.nextInt();
				N_prob[k][1] = input.nextInt();
				N_prob[k][2] = input.nextInt();
				N_prob[k][3] = input.nextInt();
			}
			
			/*
			 * �ڵ带 �����ϼ���
			 */
			System.out.println("#" + i + " " + findMax(A) + " " + findMin(A));
		}
	}
	
	private static int findMin(int standPlayer) {
		int min = 0;
		
		if ( N_spec[standPlayer-1][0] >= N_prob[standPlayer-1][0] && N_spec[standPlayer-1][1] >= N_prob[standPlayer-1][1] && N_spec[standPlayer-1][2] >= N_prob[standPlayer-1][2] ) {
			min += N_prob[standPlayer-1][3];
		}
		
		return min;
	}

	private static int findMax(int standPlayer) {
		int max = 0;
		
		for ( int problem = 1 ; problem <= N ; problem++ ) {
			int player = problem;
			
			if ( player == standPlayer ) {
				max += N_prob[problem-1][3];
				continue;
			}
			
			int leftCnt = K;
			boolean solveYn = false;
			
			while ( !solveYn ) {
				if ( isSolve(player, problem, leftCnt) ) {
					solveYn = true;
					if ( player == standPlayer ) {
						max += N_prob[problem-1][3];
					}
				}
				else {
					player++;
					leftCnt--;
					if ( player > N ) {
						player = 1;
					}
				}
			}
		}
		
		return max;
	}

	private static boolean isSolve(int player, int problem, int leftCnt) {
		if ( player == A || leftCnt == 1 ) {
			return true;
		}
		
		if ( N_spec[player-1][0] >= N_prob[problem-1][0] && N_spec[player-1][1] >= N_prob[problem-1][1] && N_spec[player-1][2] >= N_prob[problem-1][2] ) {
			return true;
		}
		else {
			return false;
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
		
		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}
		
		public String next() {
			while ( tokenizer == null || !tokenizer.hasMoreTokens() ) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return tokenizer.nextToken();
		}
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
