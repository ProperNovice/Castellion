package tile;

import enums.EDefenderFaction;
import enums.EShapeType;
import interfaces.IShapeTypeAble;
import utils.Logger;

public class TileDefender extends Tile implements IShapeTypeAble {

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

	@Override
	protected void printCredentials() {

		Logger.INSTANCE.log(this.eDefenderFaction);
		Logger.INSTANCE.log(this.eShapeType);

	}

	public EDefenderFaction getDefenderFaction() {
		return this.eDefenderFaction;
	}

	@Override
	public EShapeType getShapeType() {
		return this.eShapeType;
	}

}
