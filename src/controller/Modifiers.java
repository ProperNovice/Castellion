package controller;

import interfaces.ISaveLoadStateAble;
import tile.Tile;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	private Tile tileToPlay = null;

	public void setTileToPlay(Tile tileToPlay) {
		this.tileToPlay = tileToPlay;
	}

	public Tile getTileToPlay() {
		return this.tileToPlay;
	}

	private Modifiers() {
		saveStart();
	}

	@Override
	public void saveStart() {
		loadStart();
	}

	@Override
	public void loadStart() {

	}

	@Override
	public void saveState() {

	}

	@Override
	public void loadState() {

	}

}
