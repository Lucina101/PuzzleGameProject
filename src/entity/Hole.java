package entity;

public class Hole extends Entity{
	
	public Hole(int x, int y) {
		super(x, y);
		this.setName("Hole");
	}
	

	
	@Override
	public String getSymbol() {
		return "H";
	}
	
}
