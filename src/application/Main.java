package application;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.*;

public class Main extends Application{

	

	@Override
		public void start (Stage primaryStage) throws Exception {
		
		GlobalResources.backgroundMusicPlayer.play();
		
		double ratio = 1;

		String backgroundImageUrl = ClassLoader.getSystemResource("Image/OpenBackgroundImageV2.png").toString();
		
		BackgroundImage bgm = new BackgroundImage(new Image(backgroundImageUrl, 1600 * ratio, 800 * ratio,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT); 
	
		try {
			Pane pane = new Pane();
			
			Scene scene = new Scene(pane, 1500 * ratio, 700 * ratio);
	 			
	 		pane.setBackground(new Background(bgm));
	 		
	 		Tooltip tooltip = new Tooltip();
	 		tooltip.setFont(new Font(12));
	 		tooltip.setText("Click to play");
			
			String accessMapButtonImageUrl = ClassLoader.getSystemResource("Image/StandardMapsButton.PNG").toString();
			Image accessMapButtonImage = new Image(accessMapButtonImageUrl, 700, 80, false , false);
			ImageView accessMapButtonImageView = new ImageView();
			accessMapButtonImageView.setImage(accessMapButtonImage);
			
			ImageView instructionImageView = new ImageView();
			String instructionImageUrl = ClassLoader.getSystemResource("Image/howToPlay.png").toString();
			Image instructionImage = new Image(instructionImageUrl, 500, 60, false, false);
			instructionImageView.setImage(instructionImage);
			
			instructionImageView.setOnMouseMoved((MouseEvent e) -> {
				tooltip.setText("Help");
				tooltip.show(accessMapButtonImageView, e.getScreenX(), e.getScreenY()+10);
			});
			
			instructionImageView.setOnMouseExited((MouseEvent e) -> {
				tooltip.hide();
				tooltip.setText("Click to play");
			});
			
			instructionImageView.setOnMouseClicked((MouseEvent e) -> {
				DrawUtil.writeHelp();
			});
			
			accessMapButtonImageView.setOnMouseClicked( (MouseEvent e) ->{
				GlobalResources.backgroundMusicPlayer.stop();
				GlobalResources.clickSound.play();
				MapListPage mapListPage = new MapListPage();
				try {
					mapListPage.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			accessMapButtonImageView.setOnMouseMoved( e -> {
				tooltip.show(accessMapButtonImageView, e.getScreenX(), e.getScreenY()+10);
			});
			
			accessMapButtonImageView.setOnMouseExited(e -> {
				tooltip.hide();
			});
			
			String createMapButtonUrl = ClassLoader.getSystemResource("Image/CreateMapButton.PNG").toString();
			Image createMapButtonImage = new Image(createMapButtonUrl, 500, 60, false , false);
			ImageView createMapButtonImageView = new ImageView();
			createMapButtonImageView.setImage(createMapButtonImage);

			createMapButtonImageView.setOnMouseClicked( (MouseEvent e) -> {
				GlobalResources.backgroundMusicPlayer.stop();
				GlobalResources.clickSound.play();
				SimulationPage simulationPage = new SimulationPage();
				simulationPage.start(primaryStage);
			});
			
			createMapButtonImageView.setOnMouseMoved( e -> {
				tooltip.show(createMapButtonImageView, e.getScreenX(), e.getScreenY()+10);
			});
			
			createMapButtonImageView.setOnMouseExited(e -> {
				tooltip.hide();
			});

			
			
			
			accessMapButtonImageView.relocate(800, 400);
			createMapButtonImageView.relocate(800, 550);
			instructionImageView.relocate(810, 675);
			
			pane.getChildren().addAll(accessMapButtonImageView, createMapButtonImageView, instructionImageView);
			
	 		primaryStage.setTitle("Chip on the grid!");
			primaryStage.setScene(scene);
			primaryStage.show();

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
}
