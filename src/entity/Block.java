package entity;

import javafx.scene.canvas.GraphicsContext;
import logic.*;

public class Block extends Entity{

	public Block(int x, int y) {
		super(x, y);
		this.setName("Block");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getSymbol() {
		return "B";
	}
	

}
