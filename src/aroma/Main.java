package aroma;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class LunchBox implements Comparable<LunchBox> {
	int heatingTime;
	int eatingTime;
	public LunchBox(int heatingTime, int eatingTime) {
		super();
		this.heatingTime = heatingTime;
		this.eatingTime = eatingTime;
	}
	public int compareTo(LunchBox o) {
		return o.eatingTime - this.eatingTime;
	}
	@Override
	public String toString() {
		return "LunchBox [heatingTime=" + heatingTime + ", eatingTime=" + eatingTime + "]";
	}
}
public class Main {
	static LunchBox[] lunchBoxes;
	static LunchBox[] sortLunchBoxes;
	static int n;
	static double totoalHeatingTime;
	static double totoalOriginHeatingTime;
	static double maxRemainEatingTime;
	
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new BufferedInputStream(new FileInputStream(Main.class.getResource("input.txt").getPath())));
		System.setIn(new BufferedInputStream(System.in));
		Scanner sc = new Scanner(System.in);
		int testCase = Integer.parseInt(sc.nextLine());
		while (testCase-- > 0) {
			totoalHeatingTime = 0;
			maxRemainEatingTime = 0;
			
			n = sc.nextInt();
			lunchBoxes = new LunchBox[n];
			sortLunchBoxes = new LunchBox[n];
			for (int i = 0; i < n; i++) {
				int h = sc.nextInt();
				lunchBoxes[i] = new LunchBox(h, 0);
				totoalHeatingTime += h;
			}
			for (int i = 0; i < n; i++) {
				lunchBoxes[i].eatingTime = sc.nextInt();
			}
			
			//lunchBoxes를 먹는시간의 내림차순으로 정렬함
			Arrays.sort(lunchBoxes);
//			for (int i = 0; i < n; i++) {
//				System.out.println(lunchBoxes[i]);
//			}
			
			totoalOriginHeatingTime = totoalHeatingTime;
			solve();
			System.out.printf("%.0f\n", (totoalOriginHeatingTime + maxRemainEatingTime));
		}
		sc.close();
	}
	private static void solve() {
		for (int i = 0; i < n; i++) {
			totoalHeatingTime -= lunchBoxes[i].heatingTime;
//			System.out.println("totoalHeatingTime : " + totoalHeatingTime);
			
			//현재 지금 먹을 도시락의 eatingtime이  앞으로 남은 도시락 해동 시간의 총합보다 클 경우에는
			//도시락이 모두 해동되고 나서도 밥을 먹고 있다.
			//추가 시간으로 챙겨 둬야함
			if(lunchBoxes[i].eatingTime > totoalHeatingTime){
				double remainEatingTime = lunchBoxes[i].eatingTime - totoalHeatingTime;
				//그러한 추가 시간 중에서 가장 큰놈을 통해서  점심시간이 확정된다.
				maxRemainEatingTime  = remainEatingTime > maxRemainEatingTime ? remainEatingTime : maxRemainEatingTime;
			}
		}
	}
}