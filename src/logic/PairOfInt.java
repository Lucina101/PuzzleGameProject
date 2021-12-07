package logic;

public class PairOfInt {

	private int first;
	private int second;
	
	public PairOfInt (int first, int second) {
		this.setFirst(first);
		this.setSecond(second);
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	@Override 
	public String toString() {
		return this.getFirst() + " " + this.getSecond();
	}
}
