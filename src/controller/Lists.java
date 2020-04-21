package controller;

import enums.EDefenderFaction;
import enums.EShapeType;
import interfaces.ISaveLoadStateAble;
import tile.Tile;
import tile.TileDefenderSafe;
import utils.ArrayList;
import utils.CoordinatesBuilder;
import utils.ListImageViewAbles;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Lists implements ISaveLoadStateAble {

	INSTANCE;

	public ArrayList<ISaveLoadStateAble> iSaveLoadStateAbles = new ArrayList<ISaveLoadStateAble>();
	public ListImageViewAbles<Tile> deckNormal, deckSafe, drawNormal, drawSeer;
	public Board board = null;

	public void instantiate() {

		createLists();
		createDeckSafe();
		startGame();

		Logger.INSTANCE.logNewLine("lists instantiated -> " + this.iSaveLoadStateAbles.size());

	}

	public void startGame() {

		// deck

		createDeck();

		// deck safe

		this.deckSafe.getArrayList().loadStart();
		this.deckSafe.getArrayList().shuffle();
		this.deckSafe.toFrontFirstImageView();
		this.deckSafe.relocateImageViews();

	}

	private void createDeck() {

		this.deckNormal.getArrayList().clear();
		this.deckNormal.getArrayList().addAll(TileDeck.INSTANCE.getDeck());
		this.deckNormal.toFrontFirstImageView();
		this.deckNormal.relocateImageViews();

	}

	private void createDeckSafe() {

		for (EDefenderFaction eDefenderFaction : EDefenderFaction.values())
			for (EShapeType eShapeType : EShapeType.values())
				this.deckSafe.getArrayList().addLast(new TileDefenderSafe(eDefenderFaction, eShapeType));

		this.deckSafe.getArrayList().saveStart();

	}

	private void createLists() {

		// deck

		this.deckNormal = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckNormal)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deck safe

		this.deckSafe = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckSafe)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// draw normal

		this.drawNormal = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDrawNormal)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// draw seer

		this.drawSeer = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsTile)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDrawSeer).build(),
				4);

		// board

		this.board = Board.INSTANCE;

	}

	@Override
	public void saveStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveStart();

	}

	@Override
	public void loadStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadStart();

	}

	@Override
	public void saveState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveState();

	}

	@Override
	public void loadState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadState();

	}

}
