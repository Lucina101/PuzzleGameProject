package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.GlobalResources;

public class DrawUtil {
	
	
	public static void writeGameOver() {
		Image gameOverImage = new Image(ClassLoader.getSystemResource("Image/GameOver.png").toString());
		AudioClip gameOverSound = new AudioClip(ClassLoader.getSystemResource("Audio/gameOver.mp3").toString());

		writePopUpImage(gameOverImage, gameOverSound, "Game Over :(");
		
		/// currently no sound, don't forget to updated it!
	}
	
	public static void writeCongrat() {
		
		Image congratImage = new Image(ClassLoader.getSystemResource("Image/congratImage.PNG").toString());
		AudioClip congratSound = new AudioClip(ClassLoader.getSystemResource("Audio/congratSound.mp3").toString());
		writePopUpImage(congratImage, congratSound, "CONGRATS!");
		
		/// currently no sound, don't forget to updated it!

		
	}	
	
	
	public static void setStyleButton(Button button) {
		button.setPrefWidth(300);
		button.setPrefHeight(60);
		button.setFont(Font.font("Algerian", 20));
		button.setStyle("-fx-text-fill: blue");

	///	button.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	

	
	public static void writePopUpImage(Image image, AudioClip sound, String title) {
        ImageView imageView;
        BorderPane pane;
        Scene scene;
        Stage stage;
        
        imageView = new ImageView(image);

        pane = new BorderPane();
        pane.setCenter(imageView);
        scene = new Scene(pane);

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);

        stage.setOnCloseRequest(
            e -> {
                e.consume();
                stage.close();
            }
        );       
        sound.play();

        stage.show();
	}
	
	public static void writeHelp() {
		
		Image img = new Image(ClassLoader.getSystemResource("Image/help.png").toString(), 1500, 700, false , false);
		writePopUpImage(img, GlobalResources.clickSound, "Help");
	}
}
