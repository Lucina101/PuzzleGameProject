package application;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import entity.Chip;
import gui.ControlPane;
import gui.Grid;
import gui.ItemPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.*;

public class SimulationPage extends Application {

	protected HBox root = new HBox();
	protected ControlPane controlPane = new ControlPane();
	protected Grid grid;

	
	public void start(Stage stage) {
		
		GlobalResources.onMap = true;
		GlobalResources.rebuildController();
	
		controlPane.getReturnToPreviousPageButton().setOnMouseClicked(e->{
			returnToPreviousPageHandle(stage);
		});;
		
		setSimulatingClickHandle();
	
		setOnResizeButtonHandler();	
	
		setOnRollbackButton();
	
		setOnForwardButton();
	
		grid = new Grid(controlPane.getRowSize().getNumber(), controlPane.getColumnSize().getNumber());

	
		GlobalResources.gameController.setCurrentMap(grid.getGameMap());
		
		GlobalResources.gameController.setGrid(grid);

		root.getChildren().addAll(controlPane, grid);
		
		Scene scene = new Scene(root, 1500, 700);	
	
		setKeyPressHandle(scene);

		stage.setScene(scene);
		stage.setTitle("Simulation Page");
		stage.show();
	

	
	}
	
	protected void returnToPreviousPageHandle(Stage stage) {
		GlobalResources.clickSound.play();
		GlobalResources.onMap = false;
		Main mainpage = new Main();
		try {
			mainpage.start(stage);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}
	

	private void setOnResizeButtonHandler () {
		controlPane.getResizeButton().setOnMouseClicked( e->{
		root.getChildren().remove(grid);
		
		Grid newGrid = new Grid(grid.getGameMap().resizeGameMap(controlPane.getRowSize().getNumber(), controlPane.getColumnSize().getNumber()));
		
		GlobalResources.rebuildController();
		GlobalResources.gameController.setGrid(newGrid);
		GlobalResources.gameController.setCurrentMap(newGrid.getGameMap());
		
		grid = newGrid;
		grid.draw();
		
		
		root.getChildren().add(grid);
		});
	}
	


	protected void setOnRollbackButton() {
		
		controlPane.getRollbackButtonPane().setOnMouseClicked(e -> {
			this.rollbackHandle();

		});
	}
	
	protected void rollbackHandle() {
		if (GlobalResources.gameController.getCurrentTime() > 0) {
			if (GlobalResources.gameController.rollback())			
			GlobalResources.gameController.setGameOver(false);
			GlobalResources.clickSound.play();

			this.updateGrid();		
		}
		

	}
	
	protected void setOnForwardButton () {
		controlPane.getForwardButtonPane().setOnMouseClicked(e -> {
			
			GlobalResources.clickSound.play();
			if (GlobalResources.gameController.forward())
				this.updateGrid();
		});
	}
	
	protected void updateGrid () {
		root.getChildren().remove(grid);
		grid = new Grid(GlobalResources.gameController.getCurrentMap());
		GlobalResources.gameController.setGrid(grid);
		grid.draw();
		root.getChildren().add(grid);		
		
		GlobalResources.gameController.setCurrentMap(grid.getGameMap());
		GlobalResources.gameController.updateChipLocation();
	}
	
	protected void setKeyPressHandle (Scene s) {
		controlPane.setOnKeyPressed(e-> {
			
			
			if (!GlobalResources.gameController.isSimulating()) return ;
			
			if (GlobalResources.gameController.isGameOver()) return ;
			
	
			boolean isMoved = false;
			
			
			if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
				isMoved = GlobalResources.gameController.moveChips(Direction.LEFT);
			} else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
				isMoved =  GlobalResources.gameController.moveChips(Direction.RIGHT);
			} else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
				isMoved = GlobalResources.gameController.moveChips(Direction.UP);
			} else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
				isMoved = GlobalResources.gameController.moveChips(Direction.DOWN);
			} else return ;
		
			
			
			GlobalResources.moveSound.play();

			if (isMoved) {			
				this.successfulMoveHandle();
			}
		});;
		
	}
	
	protected void successfulMoveHandle () {
		root.getChildren().remove(grid);
		
		int currentTime = GlobalResources.gameController.getCurrentTime();
		
		GlobalResources.gameController.keepHistoryUpto(currentTime);
		
		grid = new Grid(GlobalResources.gameController.getCurrentMap());
		GlobalResources.gameController.setGrid(grid);				
		
		if (!GlobalResources.gameController.isGameOver())
			GlobalResources.gameController.getGameMapHistory().add(grid.getGameMap());
	
		GlobalResources.gameController.forwardCurrentTime();
		
		

		if (!GlobalResources.gameController.isGameOver())
			GlobalResources.gameController.updateChipLocation();
		grid.draw();
		root.getChildren().add(grid);
	}
	
	private void setSimulatingClickHandle() {
		this.controlPane.getSimulatingButton().setOnMouseClicked(e -> {
			
			GlobalResources.clickSound.play();
			if (GlobalResources.gameController.isSimulating()) {
				GlobalResources.gameController.setSimulating(false);
			
				controlPane.enableItemPane();
				controlPane.enableResizeButton();
				if (GlobalResources.gameController.isGameOver()) {
					this.rollbackHandle();
					GlobalResources.gameController.setGameOver(false);
				}
				
				
				this.controlPane.getSimulatingButton().setText("Start simulation");
			} else {
				
				controlPane.disableItemPane();
				controlPane.disableResizeButton();
				controlPane.getItemPane().resetButtonsBackGroundColor();
				GlobalResources.gameController.setSimulating(true);
				
				if (GlobalResources.gameController.getGameMapHistory().size() == 0) {
					GlobalResources.gameController.getGameMapHistory().add(grid.getGameMap());
					GlobalResources.gameController.setCurrentTime(0);
				}

				
				GlobalResources.gameController.updateChipLocation();
				GlobalResources.gameController.setCurrentMap(grid.getGameMap()); 						
				
				if (GlobalResources.gameController.isWin()) {
					DrawUtil.writeCongrat();
				}

				this.controlPane.getSimulatingButton().setText("Stop simulation");
			}
		});
	}
	
	public static void main (String[] args) {
		launch(args);
		
	}
	
	
	

}
