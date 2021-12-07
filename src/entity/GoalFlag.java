package entity;

public class GoalFlag extends Entity {

	public GoalFlag(int x, int y) {
		super(x, y);
		this.setName("GoalFlag");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSymbol() {
		return "GF";
	}
	
}
