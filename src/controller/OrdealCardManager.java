package controller;

import cards.CardOrdeal;
import enums.EOrdealAbility;
import utils.ArrayList;
import utils.HashMap;

public enum OrdealCardManager {

	INSTANCE;

	private HashMap<Integer, ArrayList<CardOrdeal>> list = new HashMap<Integer, ArrayList<CardOrdeal>>();

	private OrdealCardManager() {
		createList();
	}

	private void createList() {

		for (int counter = 1; counter <= 3; counter++)
			this.list.put(counter, new ArrayList<CardOrdeal>());

		// 1

		this.list.getValue(1).addLast(new CardOrdeal(EOrdealAbility.BASTION, 2, 16, "1a"));
		this.list.getValue(1).addLast(new CardOrdeal(EOrdealAbility.LINE_OF_DEFENCE, 2, 16, "1b"));
		this.list.getValue(1).addLast(new CardOrdeal(EOrdealAbility.FOUNDATION, 1, "1c"));
		this.list.getValue(1).addLast(new CardOrdeal(EOrdealAbility.TOWER, 2, 16, "1d"));

		// 2

		this.list.getValue(2).addLast(new CardOrdeal(EOrdealAbility.LINE_OF_DEFENCE, 3, 32, "2a"));
		this.list.getValue(2).addLast(new CardOrdeal(EOrdealAbility.TOWER, 3, 32, "2b"));
		this.list.getValue(2).addLast(new CardOrdeal(EOrdealAbility.FOUNDATION, 2, "2c"));
		this.list.getValue(2).addLast(new CardOrdeal(EOrdealAbility.BASTION, 3, 32, "2d"));

		// 3

		this.list.getValue(3).addLast(new CardOrdeal(EOrdealAbility.LINE_OF_DEFENCE, 5, 42, "3a"));
		this.list.getValue(3).addLast(new CardOrdeal(EOrdealAbility.BASTION, 5, 42, "3b"));
		this.list.getValue(3).addLast(new CardOrdeal(EOrdealAbility.TOWER, 5, 42, "3c"));
		this.list.getValue(3).addLast(new CardOrdeal(EOrdealAbility.FOUNDATION, 3, "3d"));

	}

	public ArrayList<CardOrdeal> getSet() {

		ArrayList<CardOrdeal> set = new ArrayList<CardOrdeal>();

		set.addLast(this.list.getValue(1).getRandom());
		set.addLast(this.list.getValue(2).getRandom());
		set.addLast(this.list.getValue(3).getRandom());

		return set;

	}

}
