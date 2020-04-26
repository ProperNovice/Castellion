package controller;

import cards.CardOrdeal;
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

	public ArrayList<ArrayList<? extends Object>> lists = new ArrayList<>();
	public ListImageViewAbles<Tile> deckNormal, deckSafe, drawNormal, drawSeer, discardPile, discardPileChameleon;
	public ListImageViewAbles<CardOrdeal> ordealCards;
	public Board board = null;

	public void instantiate() {

		createLists();
		createDeckSafe();
		startGame();

		OrdealCardTraitorList.INSTANCE.instantiate();

		Logger.INSTANCE.logNewLine("lists instantiated -> " + this.lists.size());

	}

	public void startGame() {

		for (ArrayList<? extends Object> list : this.lists)
			list.clear();

		// deck

		createDeck();

		// deck safe

		this.deckSafe.getArrayList().loadStart();
		this.deckSafe.getArrayList().shuffle();
		this.deckSafe.toFrontFirstImageView();
		this.deckSafe.relocateImageViews();

		// ordeal cards

		createOrdealCards();

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

	private void createOrdealCards() {

		this.ordealCards.getArrayList().addAll(OrdealCardManager.INSTANCE.getSet());

		for (CardOrdeal ordealCard : this.ordealCards) {
			ordealCard.getImageView().setVisible(true);
			ordealCard.getImageView().flipBack();
		}

		this.ordealCards.relocateImageViews();

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

		// discard pile

		this.discardPile = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDiscardPile)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// discard pile chameleon

		this.discardPileChameleon = new ListImageViewAbles<Tile>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsTile)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDiscardPileChameleon).build());

		// ordeal cards

		this.ordealCards = new ListImageViewAbles<CardOrdeal>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCard)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesOrdealCards).build());

	}

	@Override
	public void saveStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.lists)
			iSaveLoadStateAble.saveStart();

	}

	@Override
	public void loadStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.lists)
			iSaveLoadStateAble.loadStart();

	}

	@Override
	public void saveState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.lists)
			iSaveLoadStateAble.saveState();

	}

	@Override
	public void loadState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.lists)
			iSaveLoadStateAble.loadState();

	}

}
