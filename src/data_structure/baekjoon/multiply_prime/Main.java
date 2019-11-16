package data_structure.baekjoon.multiply_prime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	
	private static int K, N;
	private static int[] prime;

	// 백준 알고리즘
	// https://www.acmicpc.net/problem/2014
	// 소수의 곱 : 우선순위 큐
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		prime = new int[K+1];
		Queue<Integer> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= K; i++) {
			prime[i] = Integer.parseInt(st.nextToken());
			pq.add(prime[i]);
		}
		
		long ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = pq.poll();
			for (int j = 1; j <= K; j++) {
				if (prime[j] * ans <= Integer.MAX_VALUE) {
					pq.add(prime[j] * (int) ans);
				}
				
				if (ans % prime[j] == 0) {
					break;
				}
			}
		}
		bw.write(ans + "\n");
		bw.close();
	}
}
