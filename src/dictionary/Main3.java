package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main3 {
	static String[] inputWord;
	static boolean[][] order = new boolean[26][26];
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("test.txt"));
		
		int testCount = sc.nextInt();
		
		while ( testCount-- > 0 ) {
			int wordCnt = sc.nextInt();
			
			
			//단어 배열에 담기
			inputWord = new String[wordCnt];
			inputWord[0] = sc.next();
			for ( int i = 1 ; i < wordCnt ; i++ ) {
				inputWord[i] = sc.next();
				if ( !checkSameChar(inputWord[i-1].charAt(0), inputWord[i].charAt(0))) {
					makeOrder(inputWord[i-1].charAt(0), inputWord[i].charAt(0));
				}
			}
			
			for ( int i = 1 ; i < wordCnt ; i++ ) {
				if ( checkSameChar(inputWord[i-1].charAt(0), inputWord[i].charAt(0))) {
					for ( int j = 1 ; j < inputWord[i].length() ; j++ ) {
						if ( !checkSameChar(inputWord[i-1].charAt(j), inputWord[i].charAt(j)) ) {
							makeOrder(inputWord[i-1].charAt(j), inputWord[i].charAt(j));
							break;
						}
					}
				}
			}
			
			//order List에 담기
			String tmpOrder = String.valueOf(inputWord[0].charAt(0));
			for ( int i = 1 ; i < wordCnt ; i++ ) {
				if ( !checkSameChar(inputWord[i-1].charAt(0), inputWord[i].charAt(0))) {
					if ( checkOrder(inputWord[i-1].charAt(0), inputWord[i].charAt(0)) ) {
						tmpOrder += String.valueOf(inputWord[i].charAt(0));
					}
					else {
						System.out.println("INVALID HYPOTHESIS");
						break;
					}
				}
				else {
					if ( checkSameChar(inputWord[i-1].charAt(0), inputWord[i].charAt(0))) {
						for ( int j = 1 ; j < inputWord[i].length() ; j++ ) {
							if ( !checkSameChar(inputWord[i-1].charAt(j), inputWord[i].charAt(j)) ) {
								if ( checkOrder(inputWord[i-1].charAt(j), inputWord[i].charAt(j)) ) {
									tmpOrder += String.valueOf(inputWord[i].charAt(0));
								}
								else {
									System.out.println("INVALID HYPOTHESIS");
									break;
								}
							}
						}
					}
				}
			}
			System.out.println(tmpOrder);
		}
		
		sc.close();
	}

	private static boolean checkOrder(char frontChar, char behindChar) {
		int a = frontChar - 'a';
		int b = behindChar - 'a';
		
		return order[a][b];
	}

	private static boolean checkSameChar(char frontChar, char behindChar) {
		if ( frontChar == behindChar ) {
			return true;
		}
		else {
			return false;
		}
	}

	private static void makeOrder(char frontChar, char behindChar) {
		int a = frontChar - 'a';
		int b = behindChar - 'a';
		
		if ( !order[b][a] ) {
			order[a][b] = true;
		}
	}

}
