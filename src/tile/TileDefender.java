package tile;

import enums.EDefenderFaction;
import enums.EShapeType;

public class TileDefender extends Tile {

	private EDefenderFaction eDefenderFaction = null;
	private EShapeType eShapeType = null;

	public TileDefender(EDefenderFaction eDefenderFaction, EShapeType eShapeType) {

		this.eDefenderFaction = eDefenderFaction;
		this.eShapeType = eShapeType;

		String fileName = getfileName();
		createTile(fileName);

	}

	protected String getfileName() {
		return this.eDefenderFaction.getFileName() + this.eShapeType.getFileName();
	}

	public EDefenderFaction getDefenderFaction() {
		return this.eDefenderFaction;
	}

	public EShapeType getShapeType() {
		return this.eShapeType;
	}

}
