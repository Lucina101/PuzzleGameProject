package entity;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;
import logic.GameMap;
import logic.GlobalResources;
import logic.InvalidDirectionException;
import logic.PairOfInt;


public class Chip extends Entity {
	
	public Chip () {
		super(0, 0);		
		this.setName("BlackChip");

	}
	
	public Chip (int x, int y) {
		super(x, y);
		this.setName("BlackChip");
	}
	
	@Override
	public String getSymbol() {
		return "C";
	}
	
	
	public boolean move(GameMap gameMap, Direction direction) throws InvalidDirectionException {
		
		/// x, y is i, j
		
		int targetI = this.getI();
		int targetJ = this.getJ();
		
		switch (direction) {
		case LEFT:
			targetJ -= 1;
			break;
		case RIGHT:
			targetJ += 1;
			break;
		case UP:
			targetI -= 1;
			break;
		case DOWN:
			targetI += 1;
			break;
		default:
			throw new InvalidDirectionException();
		}
		
		if (!gameMap.insideBoard(targetI, targetJ)) return false;
		
		switch (gameMap.getBoard()[targetI][targetJ]) {
		case ".":
			gameMap.setString(targetI, targetJ, "C");
			gameMap.setString(this.getI(), this.getJ(), ".");
			this.setI(targetI);
			this.setJ(targetJ);
			return true;
		case "B":
			return false;
		case "H":
			gameMap.setString(this.getI(), this.getJ(), ".");
			gameMap.getDeadSpots().add(new PairOfInt(targetI, targetJ));
			GlobalResources.gameController.setGameOver(true);
			return true;	
		case "C":
			return false;
		}
		
		
		System.out.println("Unexpected move\n");
		throw new InternalError();
	}
	
}
