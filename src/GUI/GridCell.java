package gui;

import java.io.IOException;

import entity.*;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;
import logic.GlobalResources;
import logic.PairOfInt;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;


public class GridCell extends Pane{
	
	
	private Entity entity;
	private int i;
	private int j;
	private boolean hasFlag = false;
	private boolean dead = false;
	private double prefSize;
	
	public GridCell (int i, int j, double prefSize) {
	
		super();		
		this.prefSize = prefSize;
		this.setMinHeight(prefSize);
		this.setMinWidth(prefSize);
		this.setI(i);
		this.setJ(j);
		this.setPadding(new Insets(8));
		
		setBackgroundLightSalmonColor();
		
		
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent  e) {
						
						Thread thread = new Thread( ()->{
						Platform.runLater(new Runnable(){
							@Override
							public void run () {
								boolean isSuccessfulChanged = onClickHandler();
								draw();
								
								if (isSuccessfulChanged) {

									GlobalResources.gameController.clearHistory();
								}
							}
							});
						});
						thread.start();
					}
						// TODO fill in this method					}
			});

	}
	
	


	private boolean onClickHandler() {
		
		if (!GlobalResources.onMap) return false;
		
		if (GlobalResources.gameController.getSelectedItem() == null || GlobalResources.gameController.getSelectedItem().isEmpty() || GlobalResources.gameController.isSimulating()) return false;
		if (GlobalResources.gameController.getSelectedItem() == "GoalFlag") {
			if (this.entity == null || this.entity.getClass() == Chip.class) {
				setHasFlag(true);
			}
			return true;
		}
		
		if (GlobalResources.gameController.getSelectedItem() == "Destroy Tool") {
			this.entity = null;
			setHasFlag(false);
			return true; 
		}
		
		if (this.entity != null) return false;
		switch (GlobalResources.gameController.getSelectedItem()) {
		case "Chip":
			this.entity = new Chip(i, j);
			break;
		case "Block":
			this.entity = new Block(i, j);
			break;
		case "Hole":
			this.entity = new Hole(i, j);
			break;
		default:
			System.out.println("Unexpected selected item " + GlobalResources.gameController.getSelectedItem());
			throw new InternalError();
		}
		
		return true;
	}
	
	public void draw() {
		
		if (dead) {
			///this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));		
			Image deadSpotImage = new Image(ClassLoader.getSystemResource("Image/deadSpotImage.PNG").toString());

			BackgroundSize bgSize = new BackgroundSize(this.prefSize, this.prefSize,false,false,false,false);
			BackgroundImage bgImg = new BackgroundImage(deadSpotImage, null, null, null, bgSize);

			BackgroundFill bgFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
			BackgroundFill[] bgFillA = {bgFill};

			BackgroundImage[] bgImgA =  {bgImg};
			this.setBackground(new Background(bgFillA, bgImgA));
			return;
		}
		
		if (this.entity != null) {
			
			String imageURL = ClassLoader.getSystemResource("Image/" + entity.getName() + ".png").toString();
		
				
			Image img = new Image(imageURL, this.prefSize, this.prefSize, false, false);		
			
			BackgroundFill bgFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
			BackgroundFill[] bgFillA = {bgFill};

			BackgroundSize bgSize = new BackgroundSize(this.prefSize, this.prefSize,false,false,false,false);
			BackgroundImage bgImg = new BackgroundImage(img, null, null, null, bgSize);
			
			Image flagImage = new Image(ClassLoader.getSystemResource("Image/GoalFlag.png").toString(), this.prefSize, this.prefSize, false , false);
			BackgroundImage flagImageBackground = new BackgroundImage(flagImage, null, null, null, bgSize); 
			int sz = hasFlag ? 2 : 1;
			BackgroundImage[] bgImgA = new BackgroundImage[sz];				
			bgImgA[0] = bgImg;
			if (hasFlag && sz == 2) {
				String stringUrl = ClassLoader.getSystemResource("Image/ChipOnGoal.PNG").toString();
				Image realImage = new Image(stringUrl, this.prefSize, this.prefSize, false, false);
				BackgroundImage realBackground = new BackgroundImage(realImage, null, null, null, bgSize);
				this.setBackground(new Background(realBackground));
				return ;
			} 
			
			this.setBackground(new Background(bgFillA,bgImgA));

			
		} else if (hasFlag) {
			BackgroundSize bgSize = new BackgroundSize(this.prefSize, this.prefSize,false,false,false,false);
			BackgroundFill bgFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
			BackgroundFill[] bgFillA = {bgFill};

			Image flagImage = new Image(ClassLoader.getSystemResource("Image/GoalFlag.png").toString(), this.prefSize, this.prefSize, false , false);
			BackgroundImage flagImageBackground = new BackgroundImage(flagImage, null, null, null, bgSize); 
			BackgroundImage[] bgImgA =  {flagImageBackground};
			
			this.setBackground(new Background(bgFillA,bgImgA));
						
		} else {
			this.setBackgroundLightSalmonColor();
		}
	}
	

	public void setBackgroundLightSalmonColor() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));		
	}
	
	public Entity getEntity () {
		return this.entity;
	}

	public void setEntity (Entity e) {
		this.entity = e;
	}

	public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}


	public int getJ() {
		return j;
	}


	public void setJ(int j) {
		this.j = j;
	}


	public boolean isHasFlag() {
		return hasFlag;
	}


	public void setHasFlag(boolean hasFlag) {
		this.hasFlag = hasFlag;
	}


	public boolean isDead() {
		return dead;
	}


	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
