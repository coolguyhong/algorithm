package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static String[] inputWord;
	static boolean[][] order = new boolean[26][26];
	static boolean[] visited = new boolean[26];
	static int wordCnt = 0;
	static List<Integer> result = new ArrayList<Integer>();
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("test.txt"));
		
		int testCount = sc.nextInt();
		
		while ( testCount-- > 0 ) {
			wordCnt = sc.nextInt();
			inputWord = new String[wordCnt];
			
			for ( int i = 0 ; i < wordCnt ; i++ ) {
				inputWord[i] = sc.next();
			}
			
			if ( !makeGraph() ) {
				System.out.println("INVALID");
			}
			else {
				topologicalSort();
			}
			
			for ( int i : result ) {
				System.out.println(i);
			}
		}
		
		sc.close();
		
	}

	private static void topologicalSort() {
		//¾ËÆÄºª 0ºÎÅÍ Á¦±Í µ¹¸°´ç
		for ( int i = 0 ; i < visited.length ; i++ ) {
			visited[i] = false;
		}
		
		for ( int i = 0 ; i < 26 ; i++ ) {
			if ( !visited[i] ) {
				dfs(i);
			}
		}
	}
			
	private static void dfs(int here) {
		visited[here] = true;
		
		for ( int there = 0 ; there < 26 ; there++ ) {
			if ( order[here][there] && !visited[there] ) {
				dfs(there);
			}
		}
	}


	private static boolean makeGraph() {
		for ( int i = 1 ; i < wordCnt ; i++ ) {
			int j = i - 1 ;
			int length = Math.min(inputWord[j].length(), inputWord[i].length());
			
			for ( int k = 0 ; k < length ; k++ ) {
				if ( inputWord[j].charAt(k) != inputWord[i].charAt(k) ) {
					int from = inputWord[j].charAt(k) - 'a';
					int to = inputWord[i].charAt(k) - 'a';
					
					if ( order[to][from] ) {
						return false;
					}
					order[from][to] = true;
					break;
				}
			}
		}
		return true;
	}
}
