package logic;

import java.util.ArrayList;

import gui.Grid;

public class GameMap {

	private int height;
	private int width;
	
	private String board[][];
	private ArrayList<PairOfInt> allFlagLocation = new ArrayList<PairOfInt>();
	
	private ArrayList<PairOfInt> deadSpots = new ArrayList<PairOfInt>();
	
	public boolean insideBoard (int x, int y) {
		return x >= 0 && x < height && y >= 0 && y < width;
	}
	
	public GameMap (int height, int width) {
		this.setWidth(width);
		this.setHeight(height);
		
		board = new String[height][width];
		
		for (int i = 0 ; i < height; ++ i) {
			for (int j = 0 ;j < width ; ++ j) {
				board[i][j] = ".";
			}
		}
	}
	
	public GameMap (GameMapData gameMapdata) {
		
		this.setHeight(gameMapdata.getMapHeight());
		this.setWidth(gameMapdata.getMapWidth());
		
		board = new String[height][width];
		
		for (int i = 0 ; i < height ; ++ i) {
			for (int j = 0 ; j < width; ++ j)
				board[i][j] = ".";
		}
		try {
			
			for (PairOfInt p : gameMapdata.getAllBlockLocation()) {
				board[p.getFirst()][p.getSecond()] = "B";
			}
		
			for (PairOfInt p : gameMapdata.getAllChipLocation()) {
				
				if (board[p.getFirst()][p.getSecond()] != ".") 
					throw new InvalidGameMapDataException("Block and chip collide at " + p.toString());
				
				board[p.getFirst()][p.getSecond()] = "C";
			}
		
			for (PairOfInt p : gameMapdata.getAllHoleLocation()) {
				
				
				if (board[p.getFirst()][p.getSecond()] != ".")
					throw new InvalidGameMapDataException("Hole and " + (board[p.getFirst()][p.getSecond()] == "C" ? "chip" : "block") + " at " + p.toString());
				
				board[p.getFirst()][p.getSecond()] = "H";
			}
		
			this.setAllFlagLocation(gameMapdata.getAllFlagLocation());
			
			for (PairOfInt p : gameMapdata.getAllFlagLocation())  {
				
				if (board[p.getFirst()][p.getSecond()] == "H" ) {
					
					throw new InvalidGameMapDataException("Flag and hole collide at " + p.toString());
					
				} else if ( board[p.getFirst()][p.getSecond()] == "B") {
					
					throw new InvalidGameMapDataException("Flag and block collide at " + p.toString()); 
				
				}
			}
		} catch (InvalidGameMapDataException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw new InternalError();
		} catch (NullPointerException e) {
			System.err.println("Size of map is probably wrong");
			e.printStackTrace();
			throw new InternalError();
		}
	}

	
	public GameMap resizeGameMap (int newHeight, int newWidth) {
		GameMap resize = new GameMap(newHeight, newWidth);
		
		
		for (int i = 0 ; i < Math.min(height, newHeight) ; ++ i)
		for (int j = 0 ; j < Math.min(width, newWidth) ; ++ j) {
			resize.setString(i, j, board[i][j]);
		}
		
		for (PairOfInt p: allFlagLocation) {
			if (p.getFirst() < Math.min(height, newHeight) && p.getSecond() < Math.min(width, newWidth)) {
				resize.addFlagLocation(p.getFirst(), p.getSecond());
			}
		}
		
		for (PairOfInt p : deadSpots) {
			if (p.getFirst() < Math.min(height, newHeight) && p.getSecond() < Math.min(width, newWidth)) {
				resize.getDeadSpots().add(new PairOfInt(p.getFirst(), p.getSecond()));
			}
		}
		
		return resize;
	}
	
	

	public void printBoard () {
		
		for (int i = 0 ; i < height ; ++ i) {
			for (int j = 0 ;j  < width ; ++ j) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setString (int i, int j, String target) {
		board[i][j] = target;
	}
	
	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String board[][]) {
		this.board = board;
	}

	public ArrayList<PairOfInt> getAllFlagLocation() {
		return allFlagLocation;
	}

	public void setAllFlagLocation(ArrayList<PairOfInt> allFlagLocation) {
		this.allFlagLocation = allFlagLocation;
	}
	
	public void addFlagLocation(int i, int j) {
		this.allFlagLocation.add(new PairOfInt(i, j));
	}

	public ArrayList<PairOfInt> getDeadSpots() {
		return deadSpots;
	}

	public void setDeadSpots(ArrayList<PairOfInt> deadSpots) {
		this.deadSpots = deadSpots;
	}
}
