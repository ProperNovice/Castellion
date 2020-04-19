package controller;

import interfaces.ISaveLoadStateAble;
import utils.ArrayList;
import utils.Logger;

public enum Lists implements ISaveLoadStateAble {

	INSTANCE;

	public ArrayList<ISaveLoadStateAble> iSaveLoadStateAbles = new ArrayList<ISaveLoadStateAble>();

	public void instantiate() {

		createLists();

		Logger.INSTANCE.logNewLine("lists instantiated -> " + this.iSaveLoadStateAbles.size());

	}

	private void createLists() {

	}

	@Override
	public void saveStartGame() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveStartGame();

	}

	@Override
	public void loadStartGame() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadStartGame();

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
