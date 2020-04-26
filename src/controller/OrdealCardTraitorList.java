package controller;

import cards.CardOrdeal;
import tile.Tile;
import utils.CoordinatesBuilder;
import utils.ListImageViewAbles;

public enum OrdealCardTraitorList {

	INSTANCE;

	public ListImageViewAbles<Tile> list = new ListImageViewAbles<Tile>(new CoordinatesBuilder()
			.dimensionsNumbersPair(Credentials.INSTANCE.DimensionsTile).gapY(0).objectsPerRow(1).build(), 2);

	public void instantiate() {

	}

	public void addTraitorTileRelocate(Tile tile) {

		this.list.getArrayList().addLast(tile);

		CardOrdeal ordealCard = Lists.INSTANCE.ordealCards.getArrayList().getFirst();
		
		double x = ordealCard.getImageView().getLayoutX();
		x += Credentials.INSTANCE.DimensionsCard.x / 2;
		x -= Credentials.INSTANCE.DimensionsTile.x / 2;
		double y = ordealCard.getImageView().getLayoutY();
		
		this.list.relocateList(x, y);
		this.list.relocateImageViews();

	}

}
