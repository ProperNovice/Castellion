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
	public ListImageViewAbles<Tile> deck, deckSafe;

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

		this.deck.getArrayList().clear();
		this.deck.getArrayList().addAll(TileDeck.INSTANCE.getDeck());
		this.deck.toFrontFirstImageView();
		this.deck.relocateImageViews();

	}

	private void createDeckSafe() {

		for (EDefenderFaction eDefenderFaction : EDefenderFaction.values())
			for (EShapeType eShapeType : EShapeType.values())
				this.deckSafe.getArrayList().addLast(new TileDefenderSafe(eDefenderFaction, eShapeType));

		this.deckSafe.getArrayList().saveStart();

	}

	private void createLists() {

		// deck

		this.deck = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeck)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deck safe

		this.deckSafe = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckSafe)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

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
