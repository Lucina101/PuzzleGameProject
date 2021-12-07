package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import gui.Grid;
import gui.GridCell;
import logic.*;
import mapList.MapList;

public class MapListPage extends Application{

	private VBox buttonBox = new VBox();
	private ScrollPane scrollPane = new ScrollPane();
	private ObservableList<Button> buttonList = FXCollections.observableArrayList();
	private HBox root = new HBox();
	private Grid previewGrid;
	private int lastClick = 0;
	private Button returnButton = new Button("Return to main page");
	private Text text = new Text("Stage List");
	
	@Override
	public void start(Stage stage) throws Exception {
		GlobalResources.backgroundMusicPlayerV2.play();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().add(text);
		text.setStyle("-fx-text-fill: blue");
		text.setFont(Font.font("Algerian", 40));
		
		buttonBox.setSpacing(15);
		buttonBox.setMinWidth(400);
		for (int i = 0 ; i < (int)MapList.mapList.size(); ++ i) {
			Button mapButton = new Button("Stage " + (i + 1) + "\n");
			mapButton.setText("Stage " + (i + 1) + "\n" + MapList.mapList.get(i).getMapName());
			buttonBox.getChildren().add(mapButton);
			mapButton.setMinHeight(100);
			this.setOnMouseClick(mapButton, i, stage);
			DrawUtil.setStyleButton(mapButton);
			mapButton.setFont((Font.font("Algerian", 18)));
			mapButton.setAlignment(Pos.CENTER);
		}
		scrollPane.setContent(buttonBox);
		
		buttonBox.getChildren().add(returnButton);
		DrawUtil.setStyleButton(returnButton);
		returnButton.setOnMouseClicked(e->{
			Main newMain = new Main();
			GlobalResources.backgroundMusicPlayerV2.stop();
			try {
				newMain.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		previewGrid = new Grid(new GameMap(MapList.mapList.get(0)));
		previewGrid.draw();
		root.getChildren().addAll(scrollPane, previewGrid);       
		Scene scene = new Scene(root, 1500, 700);
        stage.setScene(scene);
        stage.setTitle("Stage list");
        stage.show();

	}
	
	private void setOnMouseClick(Button mapButton, int index, Stage stage) {
		mapButton.setOnMouseClicked(e->{
			if (index != lastClick) {			
			root.getChildren().remove(previewGrid);
			previewGrid = new Grid(new GameMap(MapList.mapList.get(index)));
			previewGrid.draw();
			root.getChildren().add(previewGrid);
			lastClick = index;
			} else {
				MapPage mapPage = new MapPage(MapList.mapList.get(index));
				GlobalResources.backgroundMusicPlayerV2.stop();
				mapPage.start(stage);
			}
		});

	}
	
	public static void main (String [] args) {
		launch(args);
	}

}
