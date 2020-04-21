package tile;

public class TileEmpty extends Tile {

	public TileEmpty() {

		createTile("Empty");
		getImageView().flipFront();
		getImageView().setVisible(false);

	}

}
