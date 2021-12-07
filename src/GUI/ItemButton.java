package gui;

import entity.Entity;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ItemButton extends Button {
	
	private Item item;
	private Tooltip tooltip;
	
	public ItemButton (String type) {
		super();
		
		if (type == null) {
			this.setPrefHeight(60);
			this.setPrefWidth(60);
			this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			
			return ;
		}
		
		this.item = new Item(type);
		this.setPadding(new Insets(5));
		ImageView IV = new ImageView(ClassLoader.getSystemResource(this.item.getUrl()).toString());
		IV.setFitWidth(48);
		IV.setFitHeight(48);
		this.setGraphic(IV);
		
		this.setUpTooltip();
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	public void highlight() {
		// TODO 
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void unhighlight() {
		// TODO
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	
	public void setUpTooltip() {
		tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		
		tooltip.setText(item.getDescription());
		
		this.setOnMouseMoved((MouseEvent e) -> {
			{
				tooltip.show(this, e.getScreenX(), e.getScreenY() + 12);
			}
		});
		
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}
	

	
	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	

}
