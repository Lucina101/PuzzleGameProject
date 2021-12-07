package gui;


import logic.*;
import entity.*;

public class Item {
	
	private String url ;
	private String itemName; 
	private String description;
	
	
	public Item (String type) {
		
		
		if (type == "Block") {
			setUrl("Image/Block.png");
			setItemName("Block");
			setDescription("Block: Cannot move and Chip can't pass it.");
		
		} else if (type == "Chip") {
			
			setUrl("Image/BlackChip.png");
			setItemName("Chip");
			setDescription("Chip: Moveable and all chip will be\n" + "move together.");
			
		} else if (type == "GoalFlag") {
			
			setUrl("Image/GoalFlag.png");
			setItemName("GoalFlag");
			setDescription("GoalFlag: This is your destination!");
			
		} else if (type == "Hole") {
			
			setUrl("Image/Hole.png");
			setItemName("Hole");
			setDescription("Hole: don't move into it or game over.");
			
		} else if (type == "Destroy Tool" ) {
			
			setUrl("Image/Destroy.png");
			setItemName("Destroy Tool");
			setDescription("Destroy tool: Clear the cell.");

		} else {
			System.err.println("Unexpected Entity");
			throw new InternalError();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
