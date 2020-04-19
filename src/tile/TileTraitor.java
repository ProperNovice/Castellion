package tile;

public abstract class TileTraitor extends Tile {

	public TileTraitor() {

		String filePath = "Traitor";
		filePath += getFileName();
		createTile(filePath);

	}

	protected abstract String getFileName();

}
