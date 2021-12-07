package logic;

import java.util.Comparator;

import entity.Chip;

public class EntityComparator {

	public static final Comparator<Chip> compareByI = (Chip LHS, Chip RHS)-> {
		
		if (LHS.getI() == RHS.getI()) return 0;

		return LHS.getI() < RHS.getI() ? -1 : 1;	
	};
	
	public static final Comparator<Chip> compareByJ = (Chip LHS, Chip RHS) -> {
		if (LHS.getJ() == RHS.getJ()) return 0;

		return LHS.getJ() < RHS.getJ() ? -1 : 1;	

	};

}
