package gui;

import entity.Block;
import entity.Chip;
import entity.GoalFlag;
import entity.Hole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.GameController;
import logic.GlobalResources;

public class ItemPane extends GridPane {

	private ObservableList<ItemButton> itemButtonList = FXCollections.observableArrayList();
	
	public ItemPane () {
		
		super();
		
		// TODO implements the ItemPane's constructor

		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		
		itemButtonList.add(new ItemButton("Chip"));
		itemButtonList.add(new ItemButton("Block"));
		itemButtonList.add(new ItemButton("GoalFlag"));
		itemButtonList.add(new ItemButton("Hole"));
		itemButtonList.add(new ItemButton("Destroy Tool"));

		for (ItemButton it : itemButtonList) {
			it.setOnAction(e -> {
				setSelectedButton(it);
			});
		}
		
		itemButtonList.add(new ItemButton(null));
		itemButtonList.get(5).setOnAction(e-> {
			GlobalResources.gameController.setSelectedItem(null);
			this.resetButtonsBackGroundColor();
		});
		
		this.addRow(0, itemButtonList.get(0), itemButtonList.get(1), itemButtonList.get(2));
		this.addRow(1, itemButtonList.get(3), itemButtonList.get(4), itemButtonList.get(5));
		
		
	} 
	
	public void disable() {
		for (ItemButton it : itemButtonList)
			it.setDisable(true);
	}
	
	public void enable() {
		for (ItemButton it : itemButtonList) {
			it.setDisable(false);
		}
	}

	public void setSelectedButton ( ItemButton selectedItemButton ) {
		// TODO set selectedItemButton of SimulationManager to given ItemButton
		// resetButtonsBackgroundColor and the highlight the selecteditemButton
		GlobalResources.gameController.setSelectedItem(selectedItemButton.getItem().getItemName());
		this.resetButtonsBackGroundColor();
		selectedItemButton.highlight();
	}

	public void resetButtonsBackGroundColor() {
		// TODO unhighlight each button in itemButtonList
		
		for (ItemButton it : itemButtonList) {
			it.unhighlight();
		}
	}	
	
	
}
