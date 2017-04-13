package aroma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Aroma implements Comparable<Aroma> {
	int distributionPeriod;
	int smellTime;
	
	public Aroma(int distributionPeriod, int smellTime) {
		super();
		this.distributionPeriod = distributionPeriod;
		this.smellTime = smellTime;
	}
	
	public int compareTo(Aroma aroma) {
		return aroma.smellTime - this.smellTime;
	}
}

public class Solution {
	
	static int NUMBER_OF_AROMA;
	static int[] DISTRIBUTION_PERIODS = new int[1000];
	static int[] SMELL_TIMES = new int[1000];
	static Aroma[] AROMAS;
	static Aroma[] SORT_AROMAS;
	static int MAX_SMELL_TIME;
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		
		for (int i = 1; i <= testCase; i++) {
			NUMBER_OF_AROMA = input.nextInt();
			AROMAS = new Aroma[NUMBER_OF_AROMA];
			SORT_AROMAS = new Aroma[NUMBER_OF_AROMA];
			
			for (int j = 0; j < NUMBER_OF_AROMA; j++) {
				DISTRIBUTION_PERIODS[j] = input.nextInt();
				SMELL_TIMES[j] = input.nextInt();
			}
			
			findMaximumDuration(0);
			
			System.out.println("#" + i + " " + MAX_SMELL_TIME);
		}
	}
	
	private static void findMaximumDuration(int order) {
		for (int i = 0; i < NUMBER_OF_AROMA; i++) {
			if (i == order) {
				continue;
			}
			if (DISTRIBUTION_PERIODS[i] < 0) {
				continue;
			}
			
			DISTRIBUTION_PERIODS[i] = DISTRIBUTION_PERIODS[i] - SMELL_TIMES[order];
			
			if (order < NUMBER_OF_AROMA) {
				findMaximumDuration(order+1);
			}
			
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
