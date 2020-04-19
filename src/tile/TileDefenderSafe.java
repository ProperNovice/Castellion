package tile;

import enums.EDefenderFaction;
import enums.EShapeType;

public class TileDefenderSafe extends TileDefender {

	public TileDefenderSafe(EDefenderFaction eDefenderFaction, EShapeType eShapeType) {
		super(eDefenderFaction, eShapeType);
	}

	@Override
	protected String getfileName() {
		return super.getfileName() + "Safe";
	}

	@Override
	protected String getBackFileName() {
		return super.getBackFileName() + "Safe";
	}

}
