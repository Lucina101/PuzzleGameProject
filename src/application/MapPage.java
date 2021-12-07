package application;

import javafx.stage.Stage;
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


public class MapPage extends SimulationPage {

	
	public MapPage (GameMapData mapData) {
		this.root = new HBox();
		GlobalResources.rebuildController();
		
		this.setOnRollbackButton();
		this.setOnForwardButton();
		
		this.controlPane.minimizeControlPane();
		this.controlPane.getReturnToPreviousPageButton().setText("Return to stage list page");
		this.controlPane.getReturnToPreviousPageButton().setMinWidth(350);
		
		GameMap gameMap = new GameMap(mapData);
		
		this.grid = new Grid(gameMap);
		
		GlobalResources.gameController.setCurrentMap(this.grid.getGameMap());
		
		GlobalResources.gameController.setGrid(this.grid);
		this.grid.draw();		
		
		Image cuteGif = GlobalResources.getRandomGIF();
		ImageView gifImageView = new ImageView();
		gifImageView.setImage(cuteGif);
		
		this.controlPane.getChildren().add(gifImageView);

		this.root.getChildren().addAll(this.controlPane, this.grid);
		
	}
	
	@Override
	protected void returnToPreviousPageHandle(Stage stage) {
			GlobalResources.clickSound.play();
			GlobalResources.onMap = false;
			MapListPage mapListPage = new MapListPage();
			try {
				mapListPage.start(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		

	}
	
	
	@Override 
	public void start (Stage stage) {
		GlobalResources.onMap = true;
		Scene scene = new Scene(root, 1500, 700);	
	
		this.setKeyPressHandle(scene);
		this.controlPane.getReturnToPreviousPageButton().setOnMouseClicked(e->{
			this.returnToPreviousPageHandle(stage);
		});
		
		if (GlobalResources.gameController.getGameMapHistory().size() == 0) {
			GlobalResources.gameController.getGameMapHistory().add(grid.getGameMap());
		}
		
		GlobalResources.gameController.setSimulating(true);
		GlobalResources.gameController.updateChipLocation();

		stage.setScene(scene);
		stage.setTitle("Let's play.");
		stage.show();

	}
	
	
	public static void main (String[] args) {
		launch(args);
		
	}

}
