package controller;

import enums.EShapeType;
import tile.Tile;
import tile.TileEmpty;
import utils.ArrayList;
import utils.CoordinatesBuilder;
import utils.DirectionEnum;
import utils.HashMap;
import utils.ListImageViewAbles;
import utils.NumbersPair;
import utils.ObjectPool;

public enum Board {

	INSTANCE;

	public ArrayList<ListImageViewAbles<Tile>> list = new ArrayList<ListImageViewAbles<Tile>>(6);
	private HashMap<Integer, NumbersPair> columnCoordinates = new HashMap<Integer, NumbersPair>();
	private ArrayList<ListImageViewAbles<Tile>> emptyLists = new ArrayList<ListImageViewAbles<Tile>>();

	private Board() {

		createList();
		createRowCoordinates();

	}

	public void relocateBoard() {

		removeEmptyLists();
		addEmptyLists();
		relocateLists();

	}

	private void removeEmptyLists() {

		for (ListImageViewAbles<Tile> listImageViewAbles : this.list.clone())
			if (listImageViewAbles.getArrayList().isEmpty()) {

				this.list.remove(listImageViewAbles);
				this.emptyLists.addLast(listImageViewAbles);

			}

	}

	private void addEmptyLists() {

		while (!this.emptyLists.isEmpty()) {

			if (this.list.size() % 2 == 0)
				this.list.addFirst(this.emptyLists.removeFirst());
			else
				this.list.addLast(this.emptyLists.removeFirst());

		}

	}

	private void relocateLists() {

		int startingIndex = 0;

		if (this.list.size() == 7)
			startingIndex = -1;

		for (ListImageViewAbles<Tile> listImageViewAbles : this.list) {

			listImageViewAbles.relocateList(this.columnCoordinates.get(startingIndex));
			startingIndex++;
			listImageViewAbles.relocateImageViews();

		}

	}

	private void createList() {

		while (!list.isMaxedCapacity())
			this.list.addLast(new ListImageViewAbles<Tile>(
					new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsTile)
							.directionEnumVertical(DirectionEnum.UP).objectsPerRow(1).build(),
					6));

	}

	private void createRowCoordinates() {

		double x = Credentials.INSTANCE.CoordinatesBoardMinusOne.x;
		double y = Credentials.INSTANCE.CoordinatesBoardMinusOne.y;

		for (int counter = -1; counter <= 6; counter++) {
			this.columnCoordinates.put(counter, new NumbersPair(x, y));
			x += Credentials.INSTANCE.DimensionsTile.x + Credentials.INSTANCE.DimensionsGapBetweenComponents.x;
		}

	}

	public boolean contains(Tile tile) {

		for (ListImageViewAbles<Tile> listImageViewAbles : this.list)
			if (listImageViewAbles.getArrayList().contains(tile))
				return true;

		return false;

	}

	public void handleEmptyTilesForPlacingDefenderTile(EShapeType eShapeType) {

		removeEmptyLists();

		if (this.list.isEmpty())
			handleEmptyBoard();
		else
			handleNonEmptyBoard(eShapeType);

	}

	private void addTileEmptyToList(ListImageViewAbles<Tile> list) {

		TileEmpty tileEmpty = ObjectPool.INSTANCE.acquire(TileEmpty.class);
		tileEmpty.getImageView().setVisible(true);
		list.getArrayList().addLast(tileEmpty);

	}

	private void handleEmptyBoard() {

		this.list.addLast(this.emptyLists.removeFirst());
		addTileEmptyToList(this.list.getFirst());
		relocateBoard();

	}

	private void handleNonEmptyBoard(EShapeType eShapeType) {

	}

}
