package quizshow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
//N명의 학생 2<=N<=1000
//N개의 문제
//i번째 문제는 i번 학생부터 풀 수 있음
//i번 학생이 틀리면 그 다음 i+1번 학생이 풀고 만약 N번째 학생까지 다 못풀면 맨 처음 학생부터 다시 시작함
//각 학생은 문제를 풀 수 있는 역량을 갖고 있음 언어력, 응용력, 논리력
//각 문제는 그 문제를 풀 수 있는 역량치를 갖고 있음
//각 학생이 그 문제를 풀 때 역량치를 넘으면 무조건 풀 수 있고, 넘지 않아도 찍어서 맞출 수 있음
//각 문제는 객관식이고 K개의 보기를 갖고 있음
//학생들은 똑똑해서 그 문제가 틀려도 객관식이기 때문에 앞사람이 선택한 답을 선택하지 않음
//학생들은 문제를 맞추려고 최선을 다함
	
//A학생의 최대점수와 최소점수를 구해라
	
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
			 * 코드를 구현하세요
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
