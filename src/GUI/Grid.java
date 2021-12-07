package gui;

import entity.Block;
import entity.Chip;
import entity.Hole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import logic.GameMap;
import logic.PairOfInt;

public class Grid extends GridPane {
	
	private ObservableList<GridCell> gridCells = FXCollections.observableArrayList();
	
	private int gridHeight; 
	private int gridWidth;
	
	public Grid (GameMap gameMap) {
		this.setPrefWidth(1150);
		this.setMinWidth(1150);
		this.setPrefHeight(700);
		this.setGridHeight(gameMap.getHeight());
		this.setGridWidth(gameMap.getWidth());
		
		this.setPrefWidth(500);
		this.setAlignment(Pos.CENTER);
		this.setVgap(4);
		this.setHgap(4);
		this.setPadding(new Insets(8));
		
		
		this.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		
		double prefSize = Math.min((double) 1000 / this.gridWidth, (double) 600 /this.gridHeight);
		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				this.gridCells.add(new GridCell(i, j, prefSize));
			}
		}
		
		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				this.add(gridCells.get(i * this.gridWidth + j), j, i); /// column before row
			}
		}

		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				GridCell cell = gridCells.get(i * this.gridWidth + j);
				
				switch (gameMap.getBoard()[i][j]) {
				case "B":
					cell.setEntity(new Block(i, j));
					break;
				case "C":
					cell.setEntity(new Chip(i, j));
					break;
				case "H":
					cell.setEntity(new Hole(i, j));
					break;
				}
			}
		}
		
		for (PairOfInt p : gameMap.getAllFlagLocation()) {
			gridCells.get(p.getFirst() * this.gridWidth + p.getSecond()).setHasFlag(true);;
		}
		
		for (PairOfInt p : gameMap.getDeadSpots()) {
			gridCells.get(p.getFirst() * this.gridWidth + p.getSecond()).setDead(true);
		}

		
	}
	
	public Grid (int gridHeight, int gridWidth) {
		// TODO complete the FieldPane's constructor
		this.setPrefWidth(1150);
		this.setMinWidth(1150);
		this.setPrefHeight(700);
		this.setGridHeight(gridHeight);
		this.setGridWidth(gridWidth);
		
		this.setPrefWidth(500);
		this.setAlignment(Pos.CENTER);
		this.setVgap(4);
		this.setHgap(4);
		this.setPadding(new Insets(8));
		
		
		this.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
		
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		
		double prefSize = Math.min((double) 1000 / this.gridWidth, (double) 600 /this.gridHeight);
		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				this.gridCells.add(new GridCell(i, j, prefSize));
			}
		}
		
		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				this.add(gridCells.get(i * this.gridWidth + j), j, i); /// column before row
			}
		}
	}
	
	

	
	public void draw() {
		
		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				gridCells.get(i * this.gridWidth + j).draw();
			}
		}
	}
	
	public GameMap getGameMap () {
		
		GameMap gameMap = new GameMap(this.gridHeight, this.gridWidth);
		for (int i = 0 ; i < this.gridHeight ; ++ i) {
			for (int j = 0 ; j < this.gridWidth ; ++ j) {
				int index = i * this.gridWidth + j;
				
				if (gridCells.get(index).getEntity() == null) gameMap.setString(i, j, ".");
				else {
					gameMap.setString(i, j, gridCells.get(index).getEntity().getSymbol());
				}
				
				if (gridCells.get(index).isHasFlag()) {
					gameMap.addFlagLocation(i, j);
				}
			}
		}
		
		return gameMap;
	}

	public ObservableList<GridCell> getGridCells() {
		return this.gridCells;
	}
	
	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	} 


}
