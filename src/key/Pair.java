package key;

public class Pair implements Comparable<Pair> {
	int a, b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Pair ot) {
		if (a != ot.a) {
			return a - ot.a;
		}
		
		return b - ot.b;
	}
	
	
}
