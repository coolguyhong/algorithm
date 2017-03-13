package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {
	static char[] alphabet = {'a' , 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
		'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		'x', 'y', 'z'
	};
	
	static char[] tmpOrder;
	static char[] resultOrder = new char[26];
	static String[] inputWord;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("test.txt"));
		
		int testCount = sc.nextInt();

		while (testCount-- > 0) {
			int wordCnt = sc.nextInt();
			
			//단어 배열에 담기
			inputWord = new String[wordCnt];
			tmpOrder = new char[wordCnt];
			
			inputWord[0] = sc.next();
			tmpOrder[0] = inputWord[0].charAt(0);
			int j = 1;
			for ( int i = 1 ; i < wordCnt ; i++ ) {
				inputWord[i] = sc.next();
				if ( tmpOrder[j-1] != inputWord[i].charAt(0) ) {
					tmpOrder[j] = inputWord[i].charAt(0);
					j++;
				}
			}
			boolean chk = true;
			for ( int i = 1 ; i < wordCnt ; i++ ) {
				if ( inputWord[i-1].charAt(0) == inputWord[i].charAt(0) ) {
					if ( !orderCheck(i) ) {
						chk = false;
						System.out.println("INVALID HYPOTHESIS");
						break;
					}
				}
			}
			
			if ( chk ) {
				for ( int i = 0 ; i < resultOrder.length ; i++ ) {
					System.out.println(resultOrder[i]);
				}
			}
		}
		
		sc.close();
	}

	private static boolean orderCheck(int wordOrder) {
		for ( int i = 1 ; i < inputWord[wordOrder].length() ; i++ ) {
			if ( inputWord[wordOrder-1].charAt(i) != inputWord[wordOrder].charAt(i) ) {
				if (findOrder(inputWord[wordOrder-1].charAt(i), inputWord[wordOrder].charAt(i))) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean findOrder(char frontChar, char behindChar) {
		int frontOrder = 0;
		int behindOrder = 0;
		
		for ( int i = 0 ; i < tmpOrder.length ; i++ ) {
			if ( frontChar == tmpOrder[i] ) {
				frontOrder = i;
			}
			if ( behindChar == tmpOrder[i] ) {
				behindOrder = i;
			}
		}
		
		if ( behindOrder < frontOrder ) {
			return false;
		}
		
		if ( frontOrder == 0 ) {
			for ( int i = 1 ; i < tmpOrder.length ; i++ ) {
				resultOrder[0] = frontChar;
				resultOrder[i] = tmpOrder[i-1];
			}
		}
		else {
			for ( int i = 0 ; i < tmpOrder.length ; i++ ) {
				resultOrder[i] = tmpOrder[i];
			}
		}
		
		return true;
	}

}
