package gui;

import application.DrawUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControlPane extends VBox{

	
	private Numbar rowSize;
	private Numbar columnSize;
	private Button resizeButton = new Button("Resize Grid");
	private ItemPane itemPane = new ItemPane();
	private HBox historyBar = new HBox();
	private ImageView rollbackButton = new ImageView();
	private ImageView forwardButton = new ImageView();
	private Pane rollbackButtonPane = new Pane();
	private Pane forwardButtonPane = new Pane();
	private Button simulatingButton = new Button("Start simulation");
	private Button returnToPreviousPageButton = new Button("Return To Main Page");

	
	public ControlPane() {
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(30);
		this.setPrefWidth(400);
		this.setPrefHeight(700);
		rowSize = new Numbar("row size");
		columnSize = new Numbar("colum size");
		
		Image rollbackButtonImage = new Image(ClassLoader.getSystemResource("Image/rollback.png").toString(), 96, 96, false, false);
		Image forwardButtonImage = new Image(ClassLoader.getSystemResource("Image/forward.png").toString(), 96, 96, false, false);

		BackgroundImage rollbackButtonBackground = new BackgroundImage(rollbackButtonImage,
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT); 
		
		BackgroundImage forwardButtonBackground = new BackgroundImage(forwardButtonImage,
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT); 


		rollbackButtonPane.setPrefSize(96, 96);
		forwardButtonPane.setPrefSize(96, 96);
		rollbackButtonPane.setBackground(new Background(rollbackButtonBackground));
		forwardButtonPane.setBackground(new Background(forwardButtonBackground));

		
		
		historyBar.getChildren().addAll(rollbackButtonPane, forwardButtonPane);
		historyBar.setSpacing(30);
		historyBar.setAlignment(Pos.CENTER);
		
		DrawUtil.setStyleButton(this.resizeButton);
		DrawUtil.setStyleButton(this.simulatingButton);
		DrawUtil.setStyleButton(this.returnToPreviousPageButton);
		
		this.getChildren().addAll(rowSize, columnSize, resizeButton, itemPane, historyBar, getSimulatingButton(), returnToPreviousPageButton);
		
	}
	
	public void minimizeControlPane() {
		this.getChildren().clear();
		this.getChildren().addAll(historyBar, returnToPreviousPageButton);
	}
	
	public void disableItemPane() {

		itemPane.disable();
	}
	
	public void enableItemPane() {
		itemPane.enable();
	}
	
	public void disableResizeButton() {
		resizeButton.setDisable(true);
	}
	
	public void enableResizeButton() {
		resizeButton.setDisable(false);
	}
	
	public Numbar getRowSize () {
		return this.rowSize;
	}
	
	public Numbar getColumnSize() {
		return this.columnSize;
	}

	public Button getResizeButton () {
		return this.resizeButton;
	}
	
	public Button getSimulatingButton() {
		return simulatingButton;
	}

	public void setSimulatingButton(Button simulatingButton) {
		this.simulatingButton = simulatingButton;
	}
	
	public ImageView getRollbackButton () {
		return this.rollbackButton;
	}
	
	public ImageView getForwardButton () {
		return this.forwardButton;
	}
	
	public Button getReturnToPreviousPageButton() {
		return this.returnToPreviousPageButton;
	}

	public ItemPane getItemPane() {
		return this.itemPane;
	}
	
	public Pane getRollbackButtonPane() {
		return rollbackButtonPane;
	}
		
	public void setRollbackButtonPane(Pane rollbackButtonPane) {
		this.rollbackButtonPane = rollbackButtonPane;
	}
	
	public Pane getForwardButtonPane() {
		return forwardButtonPane;
	}
	
	public void setForwardButtonPane(Pane forwardButtonPane) {
		this.forwardButtonPane = forwardButtonPane;
	}
	
	public HBox getHistoryBar () {
		return this.historyBar;
	}
	
	
}
