package mapList;

import java.util.ArrayList;

import javafx.scene.chart.PieChart.Data;
import logic.GameMapData;

public class MapList {
	
	public static ArrayList<GameMapData> mapList= new ArrayList();

	static {
		GameMapData data = new GameMapData();
		data.setMapName("Toward the goal together\nV1");
		
		data.setMapHeight(6);
		data.setMapWidth(12);
		
		data.addChipLocation(0, 0);
		data.addChipLocation(1, 0);
		data.addChipLocation(2, 0);
		data.addChipLocation(3, 0);
		data.addChipLocation(4, 0);
		data.addChipLocation(5, 0);

		data.addFlagLocaion(0, 11);
		data.addFlagLocaion(1, 11);
		data.addFlagLocaion(2, 11);
		data.addFlagLocaion(3, 11);
		data.addFlagLocaion(4, 11);
		data.addFlagLocaion(5, 11);
		
		mapList.add(data);
	}

	static {
		
		GameMapData data = new GameMapData();
		data.setMapName("Harder than first stage?");
		
		data.setMapHeight(8);
		data.setMapWidth(9);
		
		data.addChipLocation(0, 0);
		data.addChipLocation(0, 2);
		data.addChipLocation(1, 1);
		data.addChipLocation(5, 5);
		
		
		data.addHoleLocation(0, 6);
		data.addHoleLocation(1, 7);
		
		data.addFlagLocaion(2, 3);
		data.addFlagLocaion(4, 5);
		data.addFlagLocaion(5, 3);
		data.addFlagLocaion(3, 7);
		
		data.addBlockLocation(3, 1);
		data.addBlockLocation(2, 4);
		data.addBlockLocation(5, 2);
		data.addBlockLocation(4, 3);
		data.addBlockLocation(1, 6);
		
		mapList.add(data);
	}
	
	static {
		GameMapData data = new GameMapData();
		data.setMapName("Toward the goal together\nV2");
		data.setMapHeight(6);
		data.setMapWidth(13);
		
		data.addChipLocation(1, 0);
		data.addChipLocation(3, 0);
		data.addChipLocation(5, 0);
		
		data.addBlockLocation(0, 6);
		data.addBlockLocation(1, 9);
		data.addBlockLocation(0, 10);
		data.addBlockLocation(2, 11);
		data.addBlockLocation(3, 12);
		
		data.addFlagLocaion(0, 5);
		data.addFlagLocaion(0, 9);
		data.addFlagLocaion(3, 11);
		
		data.addHoleLocation(0, 3);
		data.addHoleLocation(2, 4);
		data.addHoleLocation(4, 6);
		data.addHoleLocation(1, 7);
		data.addHoleLocation(2, 9);	
		
		mapList.add(data);

	}


	static {
		GameMapData data1 = new GameMapData();
		data1.setMapName("Codeforces Problem");
		data1.setMapHeight(12);
		data1.setMapWidth(12);
		
		data1.addFlagLocaion(2, 2);
		data1.addFlagLocaion(2, 9);
		data1.addFlagLocaion(9, 2);
		data1.addFlagLocaion(9, 9);
		
		data1.addBlockLocation(5, 5);
		data1.addBlockLocation(5, 6);
		data1.addBlockLocation(6, 5);
		data1.addBlockLocation(6, 6);
			
		data1.addBlockLocation(8, 2);
		data1.addBlockLocation(8, 1);
		data1.addBlockLocation(9, 1);

		data1.addBlockLocation(8, 9);
		data1.addBlockLocation(8, 10);
		data1.addBlockLocation(9, 10);
		


		
		data1.addChipLocation(1, 1);
		data1.addChipLocation(5, 7);
		data1.addChipLocation(11, 1);
		data1.addChipLocation(11, 11);
		
		mapList.add(data1);
	}
	
}


