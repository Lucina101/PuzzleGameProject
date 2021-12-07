package logic;

import java.util.ArrayList;

public class GameMapData {
	
	private int mapWidth;
	private int mapHeight;
	
	private String mapName;

	private ArrayList<PairOfInt> allBlockLocation;
	private ArrayList<PairOfInt> allChipLocation;
	private ArrayList<PairOfInt> allFlagLocation;
	private ArrayList<PairOfInt> allHoleLocation;

	
	public GameMapData() {
		this.allBlockLocation = new ArrayList<PairOfInt>();
		this.allChipLocation = new ArrayList<PairOfInt>();
		this.allFlagLocation = new ArrayList<PairOfInt>();
		this.setAllHoleLocation(new ArrayList<PairOfInt>());

	}
	
	
	public void addBlockLocation(int i, int j) {
		this.allBlockLocation.add(new PairOfInt(i, j));
	}
	
	public void addChipLocation(int i, int j) {
		this.allChipLocation.add(new PairOfInt(i, j));
	}
	
	public void addFlagLocaion(int i, int j) {
		this.allFlagLocation.add(new PairOfInt(i, j));
	}
	
	public void addHoleLocation(int i, int j) {
		this.allHoleLocation.add(new PairOfInt(i, j));
	}
	
	public ArrayList<PairOfInt> getAllBlockLocation() {
		return allBlockLocation;
	}
	
	public void setAllBlockLocation(ArrayList<PairOfInt> allBlockLocation) {
		this.allBlockLocation = allBlockLocation;
	}
	
	public ArrayList<PairOfInt> getAllChipLocation() {
		return allChipLocation;
	}
	
	public void setAllChipLocation(ArrayList<PairOfInt> allChipLocation) {
		this.allChipLocation = allChipLocation;
	}
	
	public ArrayList<PairOfInt> getAllFlagLocation() {
		return allFlagLocation;
	}
	public void setAllFlagLocation(ArrayList<PairOfInt> allFlagLocation) {
		this.allFlagLocation = allFlagLocation;
	}

	public ArrayList<PairOfInt> getAllHoleLocation() {
		return allHoleLocation;
	}

	public void setAllHoleLocation(ArrayList<PairOfInt> allHoleLocation) {
		this.allHoleLocation = allHoleLocation;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	
}
