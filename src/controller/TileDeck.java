package controller;

import enums.EDefenderFaction;
import enums.EShapeType;
import tile.Tile;
import tile.TileDefender;
import tile.TileTraitorBlack;
import tile.TileTraitorNormal;
import utils.ArrayList;

public enum TileDeck {

	INSTANCE;

	private ArrayList<Tile> tileDefenders = new ArrayList<Tile>();
	private ArrayList<Tile> tileTraitorNormal = new ArrayList<Tile>();
	private ArrayList<Tile> tileTraitorBlack = new ArrayList<Tile>();
	private ArrayList<ArrayList<Tile>> deck = new ArrayList<ArrayList<Tile>>();

	private TileDeck() {

		createTileLists();

		this.tileDefenders.saveStart();
		this.tileTraitorNormal.saveStart();
		this.tileTraitorBlack.saveStart();

		for (int counter = 1; counter <= 12; counter++)
			this.deck.addLast(new ArrayList<Tile>(6));

	}

	private void createTileLists() {

		for (int counter = 1; counter <= 5; counter++)
			for (EDefenderFaction eDefenderFaction : EDefenderFaction.values())
				for (EShapeType eShapeType : EShapeType.values())
					this.tileDefenders.addLast(new TileDefender(eDefenderFaction, eShapeType));

		for (int counter = 1; counter <= 8; counter++)
			this.tileTraitorNormal.addLast(new TileTraitorNormal());

		for (int counter = 1; counter <= 4; counter++)
			this.tileTraitorBlack.addLast(new TileTraitorBlack());

	}

	public ArrayList<Tile> getDeck() {

		this.tileDefenders.loadStart();
		this.tileTraitorNormal.loadStart();
		this.tileTraitorBlack.loadStart();

		clearDeck();
		addDefenderTilesToDeck();
		addTraitorTilesToDeck();

		ArrayList<Tile> tiles = new ArrayList<Tile>();

		for (ArrayList<Tile> list : this.deck)
			tiles.addAll(list);

		return tiles;

	}

	private void clearDeck() {

		for (ArrayList<Tile> list : this.deck)
			list.clear();

	}

	private void addDefenderTilesToDeck() {

		while (!this.tileDefenders.isEmpty())
			for (ArrayList<Tile> list : this.deck)
				list.addLast(this.tileDefenders.removeRandom());

	}

	private void addTraitorTilesToDeck() {

		ArrayList<Tile> list = new ArrayList<Tile>();

		while (!this.tileTraitorNormal.isEmpty()) {

			for (int counter = 1; counter <= 2; counter++)
				list.addLast(this.tileTraitorNormal.removeFirst());

			list.addLast(this.tileTraitorBlack.removeFirst());

			for (ArrayList<Tile> deckList : this.deck)
				if (!list.isEmpty())
					if (!deckList.isMaxedCapacity())
						if (this.deck.indexOf(deckList) != this.deck.size() - 1)
							deckList.addLast(list.removeRandom());
						else
							deckList.addFirst(list.removeRandom());

		}

	}

}
