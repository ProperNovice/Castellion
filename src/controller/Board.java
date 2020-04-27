package controller;

import enums.EShapeType;
import interfaces.IDefenderFactionAble;
import interfaces.IShapeTypeAble;
import tile.Tile;
import tile.TileEmpty;
import utils.ArrayList;
import utils.DirectionEnum;
import utils.HashMap;
import utils.LineCast;
import utils.NumbersPair;
import utils.ObjectPool;
import utils.RealTimeDuplicateProtection;
import utils.SelectImageViewManager;
import utils.ShutDown;

public enum Board {

	INSTANCE;

	public ArrayList<Tile> listBoard = new ArrayList<Tile>(36);
	public ArrayList<TileEmpty> listEmptyTiles = new ArrayList<TileEmpty>();
	public ArrayList<Tile> listFoundation = new ArrayList<Tile>(6);
	private HashMap<Integer, NumbersPair> columnCoordinates = new HashMap<Integer, NumbersPair>();
	private HashMap<Integer, ArrayList<NumbersPair>> groupCoordinates = new HashMap<Integer, ArrayList<NumbersPair>>();

	private Board() {

		createColumnCoordinates();
		createGroupCoordinates();

		RealTimeDuplicateProtection.INSTANCE.addList(this.listBoard);

	}

	private void createColumnCoordinates() {

		double tileDistance = Credentials.INSTANCE.DimensionsTile.x
				+ Credentials.INSTANCE.DimensionsGapBetweenComponents.x;

		double x = Credentials.INSTANCE.CoordinatesBoardFirstTile.x - 3 * tileDistance;

		for (int counter = -1; counter < 6; counter++) {
			this.columnCoordinates.put(counter, new NumbersPair(x, Credentials.INSTANCE.CoordinatesBoardFirstTile.y));
			x += tileDistance;
		}

	}

	private void createGroupCoordinates() {

		int group = 0;
		ArrayList<NumbersPair> list = null;

		// 1

		group++;
		list = new ArrayList<NumbersPair>();
		list.addLast(this.columnCoordinates.getValue(3));
		this.groupCoordinates.put(group, list);

		// 2

		group++;
		list = new ArrayList<NumbersPair>();
		list.addLast(this.columnCoordinates.getValue(2));
		list.addLast(this.columnCoordinates.getValue(3));
		this.groupCoordinates.put(group, list);

		// 3

		group++;
		list = new ArrayList<NumbersPair>();
		list.addLast(this.columnCoordinates.getValue(2));
		list.addLast(this.columnCoordinates.getValue(3));
		list.addLast(this.columnCoordinates.getValue(4));
		this.groupCoordinates.put(group, list);

		// 4

		group++;
		list = new ArrayList<NumbersPair>();
		list.addLast(this.columnCoordinates.getValue(1));
		list.addLast(this.columnCoordinates.getValue(2));
		list.addLast(this.columnCoordinates.getValue(3));
		list.addLast(this.columnCoordinates.getValue(4));
		this.groupCoordinates.put(group, list);

		// 5

		group++;
		list = new ArrayList<NumbersPair>();
		list.addLast(this.columnCoordinates.getValue(1));
		list.addLast(this.columnCoordinates.getValue(2));
		list.addLast(this.columnCoordinates.getValue(3));
		list.addLast(this.columnCoordinates.getValue(4));
		list.addLast(this.columnCoordinates.getValue(5));
		this.groupCoordinates.put(group, list);

		// 6

		group++;
		list = new ArrayList<NumbersPair>();
		list.addLast(this.columnCoordinates.getValue(0));
		list.addLast(this.columnCoordinates.getValue(1));
		list.addLast(this.columnCoordinates.getValue(2));
		list.addLast(this.columnCoordinates.getValue(3));
		list.addLast(this.columnCoordinates.getValue(4));
		list.addLast(this.columnCoordinates.getValue(5));
		this.groupCoordinates.put(group, list);

	}

	public void jUnitAddTileToBoardRelocate(Tile tile, int column) {

		this.listBoard.addLast(tile);

		Tile firstTile = LineCast.INSTANCE.objectAtCoordinates(this.columnCoordinates.getValue(column));

		if (firstTile == null) {
			tile.getImageView().relocateCenter(this.columnCoordinates.getValue(column));
			return;
		}

		Tile lastTile = firstTile;

		ArrayList<Tile> list = LineCast.INSTANCE.lineCastList(firstTile, DirectionEnum.UP, 5);

		if (!list.isEmpty())
			lastTile = list.getLast();

		double y = lastTile.getImageView().getLayoutY() - Credentials.INSTANCE.DimensionsTile.y
				- Credentials.INSTANCE.DimensionsGapBetweenComponents.y;

		tile.getImageView().relocateTopLeft(lastTile.getImageView().getLayoutX(), y);

	}

	public void placeEmptyTilesForPlacingDefenderTile() {

		if (this.listBoard.isEmpty())
			placeEmptyTileOnEmptyBoard();
		else
			placeEmptyTilesOnNonEmptyBoard();

	}

	private void placeEmptyTileOnEmptyBoard() {
		placeEmptyTileToCoordinates(Credentials.INSTANCE.CoordinatesBoardFirstTile);
	}

	private void placeEmptyTilesOnNonEmptyBoard() {

		createFoundationList();

		if (!this.listFoundation.isMaxedCapacity()) {

			placeEmptyTilesAtFoundationEdge(DirectionEnum.LEFT);
			placeEmptyTilesAtFoundationEdge(DirectionEnum.RIGHT);

		}

		placeEmptyTilesAtPopulatedColumns();

	}

	private void createFoundationList() {

		this.listFoundation.clear();

		double unitX = Credentials.INSTANCE.DimensionsTile.x + Credentials.INSTANCE.DimensionsGapBetweenComponents.x;

		double startingX = Credentials.INSTANCE.CoordinatesBoardFirstTile.x;
		startingX -= 4 * unitX;
		double startingY = Credentials.INSTANCE.CoordinatesBoardFirstTile.y;

		this.listFoundation.addAll(LineCast.INSTANCE.lineCastList(startingX, startingY,
				Credentials.INSTANCE.DimensionsTile, DirectionEnum.RIGHT, 8));

	}

	private void placeEmptyTilesAtFoundationEdge(DirectionEnum directionEnum) {

		Tile tile = null;
		double x = 0, y = Credentials.INSTANCE.CoordinatesBoardFirstTile.y;

		switch (directionEnum) {

		case LEFT:
			tile = this.listFoundation.getFirst();
			x = tile.getImageView().getCenterX() - Credentials.INSTANCE.DimensionsTileAndGap.x;
			break;

		case RIGHT:
			tile = this.listFoundation.getLast();
			x = tile.getImageView().getCenterX() + Credentials.INSTANCE.DimensionsTileAndGap.x;
			break;

		default:
			ShutDown.INSTANCE.execute(this);
			break;

		}

		placeEmptyTileToCoordinates(x, y);

	}

	private void placeEmptyTileToCoordinates(double x, double y) {

		TileEmpty tileEmpty = ObjectPool.INSTANCE.acquire(TileEmpty.class);
		tileEmpty.getImageView().setVisible(true);
		tileEmpty.getImageView().relocateCenter(x, y);
		this.listEmptyTiles.addLast(tileEmpty);

	}

	private void placeEmptyTileToCoordinates(NumbersPair numbersPair) {
		placeEmptyTileToCoordinates(numbersPair.x, numbersPair.y);
	}

	private void placeEmptyTilesAtPopulatedColumns() {

		Tile tileToBePlayed = Modifiers.INSTANCE.getTileToPlay();
		IShapeTypeAble iShapeTypeAbleTileToPlay = (IShapeTypeAble) tileToBePlayed;
		EShapeType eShapeTypeToPlay = iShapeTypeAbleTileToPlay.getShapeType();

		for (Tile tile : this.listFoundation) {

			ArrayList<Tile> list = LineCast.INSTANCE.lineCastList(tile, DirectionEnum.UP, 5);

			if (list.size() == 5)
				continue;

			double tileCoordinateX = tile.getImageView().getCenterX();
			double tileCoordinateY = tile.getImageView().getCenterY()
					- (list.size() + 1) * Credentials.INSTANCE.DimensionsTileAndGap.y;

			ArrayList<Tile> adjacents = new ArrayList<Tile>();

			adjacents.addAll(LineCast.INSTANCE.lineCastList(tileCoordinateX, tileCoordinateY,
					Credentials.INSTANCE.DimensionsTile, DirectionEnum.LEFT, 1));
			adjacents.addAll(LineCast.INSTANCE.lineCastList(tileCoordinateX, tileCoordinateY,
					Credentials.INSTANCE.DimensionsTile, DirectionEnum.RIGHT, 1));
			adjacents.addAll(LineCast.INSTANCE.lineCastList(tileCoordinateX, tileCoordinateY,
					Credentials.INSTANCE.DimensionsTile, DirectionEnum.DOWN, 1));

			boolean foundDuplicate = false;

			for (Tile tileAdjacent : adjacents) {

				if (!(tileAdjacent instanceof IShapeTypeAble))
					continue;

				IShapeTypeAble iShapeTypeAble = (IShapeTypeAble) tileAdjacent;

				if (iShapeTypeAble.getShapeType().equals(eShapeTypeToPlay)) {
					foundDuplicate = true;
					break;
				}

			}

			if (foundDuplicate)
				continue;

			placeEmptyTileToCoordinates(tileCoordinateX, tileCoordinateY);

		}

	}

	public void releaseEmptyTiles() {

		for (TileEmpty tileEmpty : this.listEmptyTiles) {

			ObjectPool.INSTANCE.release(tileEmpty);
			tileEmpty.getImageView().setVisible(false);

		}

		this.listEmptyTiles.clear();

	}

	public void relocateBoard() {

		ArrayList<Tile> tilesRelocated = new ArrayList<Tile>();

		createFoundationList();
		int columns = this.listFoundation.size();

		ArrayList<NumbersPair> coordinates = this.groupCoordinates.getValue(columns);

		for (int counter = 0; counter < columns; counter++) {

			ArrayList<Tile> tiles = new ArrayList<Tile>();
			tiles.addLast(this.listFoundation.get(counter));
			tiles.addAll(LineCast.INSTANCE.lineCastList(this.listFoundation.get(counter), DirectionEnum.UP, 5));

			double x = coordinates.get(counter).x;
			double y = coordinates.get(counter).y;

			for (Tile tile : tiles) {

				if (tilesRelocated.contains(tile))
					continue;

				tile.getImageView().relocateCenter(x, y);
				tilesRelocated.addLast(tile);

				y -= Credentials.INSTANCE.DimensionsTileAndGap.y;

			}

		}

	}

	public int foundationSize() {

		createFoundationList();
		return this.listFoundation.size();

	}

	public void setTilesCanResolvedbyJuggler() {

		ArrayList<Tile> lineCast = new ArrayList<Tile>();
		ArrayList<Tile> listBoardClone = this.listBoard.clone();

		for (Tile tile : listBoardClone.clone()) {

			lineCast = LineCast.INSTANCE.lineCastList(tile, DirectionEnum.RIGHT, 1);

			if (!lineCast.isEmpty()) {

				listBoardClone.remove(tile);
				listBoardClone.remove(lineCast.getFirst());

				if (!SelectImageViewManager.INSTANCE.isSelectedImageView(tile))
					SelectImageViewManager.INSTANCE.addSelectImageView(tile);

				if (!SelectImageViewManager.INSTANCE.isSelectedImageView(lineCast.getFirst()))
					SelectImageViewManager.INSTANCE.addSelectImageView(lineCast.getFirst());

			} else {

				lineCast = LineCast.INSTANCE.lineCastList(tile, DirectionEnum.LEFT, 1);

				if (!lineCast.isEmpty()) {

					listBoardClone.remove(tile);
					listBoardClone.remove(lineCast.getFirst());

					if (!SelectImageViewManager.INSTANCE.isSelectedImageView(tile))
						SelectImageViewManager.INSTANCE.addSelectImageView(tile);

					if (!SelectImageViewManager.INSTANCE.isSelectedImageView(lineCast.getFirst()))
						SelectImageViewManager.INSTANCE.addSelectImageView(lineCast.getFirst());

				}

			}

		}

	}

	public void setUpTilesCanBeDestroyedByPyro() {

		createFoundationList();

		for (Tile tile : this.listBoard)
			SelectImageViewManager.INSTANCE.addSelectImageView(tile);

		for (Tile tile : this.listFoundation) {

			if (tile.equals(this.listFoundation.getFirst()))
				continue;

			if (tile.equals(this.listFoundation.getLast()))
				continue;

			if (!LineCast.INSTANCE.lineCastList(tile, DirectionEnum.UP, 1).isEmpty())
				continue;

			SelectImageViewManager.INSTANCE.releaseSelectImageView(tile);

		}

	}

	public void setUpTilesCanBeMovedByPyro() {

		createFoundationList();

		for (Tile tile : this.listFoundation) {

			ArrayList<Tile> list = LineCast.INSTANCE.lineCastList(tile, DirectionEnum.UP, 5);

			if (!list.isEmpty())
				SelectImageViewManager.INSTANCE.addSelectImageView(list.getLast());
			else if (this.listFoundation.getFirst().equals(tile))
				SelectImageViewManager.INSTANCE.addSelectImageView(tile);
			else if (this.listFoundation.getLast().equals(tile))
				SelectImageViewManager.INSTANCE.addSelectImageView(tile);

		}

	}

	public void setUpEmptyTilesToMoveByPyro(Tile tile) {

		for (Tile tileTemp : this.listFoundation) {

			ArrayList<Tile> list = LineCast.INSTANCE.lineCastList(tileTemp, DirectionEnum.UP, 5);

			Tile tileLast = tileTemp;

			if (!list.isEmpty())
				tileLast = list.getLast();

			if (tileLast.equals(tile))
				continue;

			if (list.size() == 5)
				continue;

			double x = tileLast.getImageView().getCenterX();
			double y = tileLast.getImageView().getCenterY();
			y -= Credentials.INSTANCE.DimensionsTileAndGap.y;

			placeEmptyTileToCoordinates(x, y);

		}

		if (!this.listFoundation.isMaxedCapacity()) {

			placeEmptyTilesAtFoundationEdge(DirectionEnum.LEFT);
			placeEmptyTilesAtFoundationEdge(DirectionEnum.RIGHT);

		} else {

			if (tile.equals(this.listFoundation.getFirst()))
				placeEmptyTilesAtFoundationEdge(DirectionEnum.RIGHT);
			else if (tile.equals(this.listFoundation.getLast()))
				placeEmptyTilesAtFoundationEdge(DirectionEnum.LEFT);

		}

	}

	public int getBastions() {

		createFoundationList();

		int bastions = 0;
		Tile tileChecking = null;

		for (Tile tileCurrent : this.listBoard.clone()) {

			// tile bottomLeft

			tileChecking = tileCurrent;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			// tile topLeft

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.UP, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			// tile topRight

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.RIGHT, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			// tile bottomRight

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.DOWN, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			bastions++;

		}

		return bastions;

	}

	public int getLines() {

		int lines = 0;

		Tile tileChecking = null;

		for (Tile tileCurrent : this.listBoard.clone()) {

			// position 1

			tileChecking = tileCurrent;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			// position 2

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.RIGHT, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			// position 3

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.RIGHT, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			// position 4

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.RIGHT, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			lines++;

		}

		return lines;

	}

	public int getTowers() {

		createFoundationList();

		int towers = 0;

		Tile tileChecking = null;

		for (Tile tileCurrent : this.listBoard.clone()) {

			// position 1

			tileChecking = tileCurrent;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.DOWN))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			// position 2

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.UP, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			// position 3

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.UP, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (!tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			// position 4

			tileChecking = (Tile) LineCast.INSTANCE.lineCastSingle(tileChecking, DirectionEnum.UP, 1);

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.LEFT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.RIGHT))
				continue;

			if (tileHasSameDefenderFactionWithTileBeside(tileChecking, DirectionEnum.UP))
				continue;

			towers++;

		}

		return towers;

	}

	private boolean tileHasSameDefenderFactionWithTileBeside(Tile tileFirst, DirectionEnum directionEnum) {

		Tile tileSecond = (Tile) LineCast.INSTANCE.lineCastSingle(tileFirst, directionEnum, 1);

		if (tileSecond == null)
			return false;

		IDefenderFactionAble iDefenderFactionAbleFirst = (IDefenderFactionAble) tileFirst;
		IDefenderFactionAble iDefenderFactionAbleSecond = (IDefenderFactionAble) tileSecond;

		return iDefenderFactionAbleFirst.getDefenderFaction().equals(iDefenderFactionAbleSecond.getDefenderFaction());

	}

	public void shiftBoard() {

		boolean retry;

		do {

			retry = false;

			createFoundationList();

			for (Tile tile : this.listBoard) {

				if (this.listFoundation.contains(tile))
					continue;

				Tile tileUnder = (Tile) LineCast.INSTANCE.lineCastSingle(tile, DirectionEnum.DOWN, 1);

				if (tileUnder != null)
					continue;

				double x = tile.getImageView().getCenterX();
				double y = tile.getImageView().getCenterY();
				y += Credentials.INSTANCE.DimensionsTileAndGap.y;
				tile.getImageView().relocateCenter(x, y);
				retry = true;

			}

		} while (retry);

		relocateBoard();

	}

	public void destroyFoundationAndShiftBoard() {

		createFoundationList();

		for (Tile tile : this.listFoundation) {
			this.listBoard.remove(tile);
			tile.getImageView().setVisible(false);
		}

		shiftBoard();
		createFoundationList();

	}

}
