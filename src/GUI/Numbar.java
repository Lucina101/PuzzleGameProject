package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Numbar extends HBox{

	
	private HBox hboxForText;
	private Text numberField;
	private int number;
	private ImageView upButton;
	private ImageView downButton;
	private Text label;
	
	public Numbar(String label) {
		super();
		
		this.label = new Text(label);
		this.label.setFont(new Font(20));
		this.label.setFill(Color.BLUE);
	
		hboxForText = new HBox();
		hboxForText.getChildren().add(this.label);
		hboxForText.setPrefHeight(60);
		hboxForText.setPrefWidth(100);
		hboxForText.setAlignment(Pos.CENTER);
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, new BorderWidths(3, 3, 3, 3))));

		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);

		
		number = 5;
		numberField = new Text();
		numberField.setText("5");
		numberField.setFont(Font.font("Verdana", 20));
		numberField.setFill(Color.BLUE);
		
		upButton = new ImageView();
		Image upButtonImage = new Image(ClassLoader.getSystemResource("Image/UpButton.png").toString(), 40, 40, false, false);
		upButton.setImage(upButtonImage);
		
		Image downButtonImage = new Image(ClassLoader.getSystemResource("Image/DownButton.png").toString(), 40, 40, false, false);
		downButton = new ImageView();
		downButton.setImage(downButtonImage);
	
		upButton.setOnMouseClicked( e-> {
			number += 1;
			number = Math.min(number, 20);
			numberField.setText("" + number);
		});
		
		downButton.setOnMouseClicked(e ->{
			number -= 1;
			number = Math.max(1, number);
			numberField.setText("" + number);
		});
		
		this.getChildren().addAll(hboxForText, this.numberField, this.upButton, this.downButton);
	}

	public Text getNumberField() {
		return numberField;
	}

	public void setNumberField(Text numberField) {
		this.numberField = numberField;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ImageView getUpButton() {
		return upButton;
	}

	public void setUpButton(ImageView upButton) {
		this.upButton = upButton;
	}

	public ImageView getDownButton() {
		return downButton;
	}

	public void setDownButton(ImageView downButton) {
		this.downButton = downButton;
	}


}
