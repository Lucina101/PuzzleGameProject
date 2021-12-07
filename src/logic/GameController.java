package logic;

import java.util.ArrayList;
import java.util.Comparator;

import application.DrawUtil;

import java.util.Collections;

import entity.*;
import gui.Grid;

public class GameController {

	
	private ArrayList<Chip> allChips = new ArrayList<Chip>();
	private ArrayList<PairOfInt> allFlagsLocation = new ArrayList<PairOfInt>();
	private int currentTime = 0;
	private GameMap currentMap;
	private Grid grid;
	private boolean isGameOver = false;
	private boolean isSimulating;
	private String selectedItem ;
		
	private ArrayList<GameMap> gameMapHistory = new ArrayList<GameMap>();
	
	public void clearHistory() {
		setCurrentTime(0);
		gameMapHistory.clear();
	}

	public void keepHistoryUpto (int targetTime) {
		
		try {
		if (targetTime < 0 ) throw new InvalidHistoryException("access negative index");
		if (targetTime >= gameMapHistory.size()) throw new InvalidHistoryException("non-allocated memory access"); 
		
		if (gameMapHistory.size() - targetTime <= targetTime) {
			ArrayList<GameMap> tempMapHistory = new ArrayList<GameMap>();
			for (int i = 0 ; i <= targetTime ; ++ i) {
				tempMapHistory.add(gameMapHistory.get(i));		
			}
			gameMapHistory = tempMapHistory;

		} else {
			while (gameMapHistory.size() - 1 != targetTime) {
				gameMapHistory.remove(gameMapHistory.size() - 1);
			}
		}
		} catch (InvalidHistoryException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			throw new InternalError();
		}
	}
	
	private void changeToTargetTime (int targetTime) {
		try {
		if (targetTime < 0 ) throw new InvalidHistoryException("access negative index");
		if (targetTime >= gameMapHistory.size()) throw new InvalidHistoryException("non-allocated memory access"); 

			
		this.setCurrentMap((new Grid(gameMapHistory.get(targetTime)).getGameMap()));
		this.setCurrentTime(targetTime);
		} catch (InvalidHistoryException e) {
			System.err.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	public void updateChipLocation() {
		currentMap = grid.getGameMap();
		allChips = new ArrayList<Chip>();
		for (int i = 0 ; i < currentMap.getHeight() ; ++ i)
		for (int j = 0 ; j < currentMap.getWidth() ; ++ j) {
			if (currentMap.getBoard()[i][j] == "C") {
				allChips.add(new Chip(i, j));
			}
		}
	}

	
	public boolean rollback () {
		
		if (currentTime > 0) {
			this.changeToTargetTime(currentTime - 1);
			return true;
		}
		
		return false;
	}
	
	public boolean forward () {

		if (currentTime + 1 < gameMapHistory.size()) {
			this.changeToTargetTime(currentTime + 1);
			return true;
		}
		
		return false;
	}
	
	public boolean moveChips(Direction direction) {
		
		boolean moveAtleastOneChip = false;
		
		try {
		if (direction == Direction.LEFT) {			
			Collections.sort(allChips, EntityComparator.compareByJ);
			
			for (Chip chip : allChips) {
				moveAtleastOneChip |= chip.move(currentMap, direction);
			}
			
		} else if (direction == Direction.RIGHT) {
			Collections.sort(allChips, EntityComparator.compareByJ);
			
			for (int i = allChips.size() - 1 ; i >= 0 ; -- i)
				moveAtleastOneChip |= allChips.get(i).move(currentMap, direction);
			
		} else if (direction == Direction.DOWN) {
			Collections.sort(allChips, EntityComparator.compareByI);
			for (int i = allChips.size() - 1 ; i >= 0 ; -- i)
				moveAtleastOneChip |= allChips.get(i).move(currentMap, direction);

			
		} else if (direction == Direction.UP) {
			Collections.sort(allChips, EntityComparator.compareByI);
			
			for (Chip chip : allChips) {
				moveAtleastOneChip |= chip.move(currentMap, direction);
			}
			
		} else {
			throw new InvalidDirectionException();
		}
		} catch (InvalidDirectionException e) {
			e.printStackTrace();
		}
		
		if (isWin()) {
			DrawUtil.writeCongrat();
		}
		
		return moveAtleastOneChip;
	}

				
	
	public boolean isWin() {
		boolean win = true;		

		for (PairOfInt p : currentMap.getAllFlagLocation()) {
			win &= (currentMap.getBoard()[p.getFirst()][p.getSecond()] == "C");
		}
		
		win &= (currentMap.getAllFlagLocation().size() > 0);
		
		
		return win;
	}
	
	public void addChip (Chip toAddChip) {
		allChips.add(toAddChip);
	}
	
	
	public void printHistory() {
		
		int count = 0;
		for (GameMap gameMap : this.gameMapHistory) {
			System.out.println("Time " + count++ + '\n');
			gameMap.printBoard();
			System.out.println();
		}
	}
	
	public void rollCurrentTimeBack() {
		-- currentTime; 
	}
	
	public void forwardCurrentTime() {
		++ currentTime;
	}

	
	///getter and setter
	
	
	public ArrayList<Chip> getAllChips() {
		return allChips;
	}
	

	
	
	public int getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(int newCurrentTime) {
		currentTime = newCurrentTime;
	}

	public GameMap getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(GameMap currentMap) {
		this.currentMap = currentMap;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		if (isGameOver == true && !this.isGameOver()) {
			DrawUtil.writeGameOver();
		}
		this.isGameOver = isGameOver;
	}

	public boolean isSimulating() {
		return isSimulating;
	}

	public void setSimulating(boolean isSimulating) {
		this.isSimulating = isSimulating;
	}

	public ArrayList<PairOfInt> getAllFlagsLocation() {
		return allFlagsLocation;
	}

	public void setAllFlagsLocation(ArrayList<PairOfInt> allFlagsLocation) {
		this.allFlagsLocation = allFlagsLocation;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}



	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}


	public ArrayList<GameMap> getGameMapHistory() {
		return gameMapHistory;
	}


	public void setGameMapHistory(ArrayList<GameMap> gameMapHistory) {
		this.gameMapHistory = gameMapHistory;
	}


}
