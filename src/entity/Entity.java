package entity;

import logic.Direction;
import logic.InvalidDirectionException;

public abstract class Entity {
	protected int i;
	protected int j;
	
	
	protected String name; 
	
	public Entity (int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public void setJ(int y) {
		this.j = y;
	}
	
	
	public abstract String getSymbol();
	
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName () {
		return this.name;
	}
	
}
