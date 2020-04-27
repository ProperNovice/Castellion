package controller;

import utils.TextIndicator;

public enum TilesToDestroyIndicator {

	INSTANCE;

	private TextIndicator textIndicator = null;

	private TilesToDestroyIndicator() {

		this.textIndicator = new TextIndicator();
		this.textIndicator.setVisible(false);
		this.textIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.textIndicator.relocateTopLeft(Credentials.INSTANCE.CoordinatesTextPanel);

	}

	public void showTextIndicator(int tiles) {

		String text = "Choose ";
		text += tiles;
		text += " tile";

		if (tiles > 1)
			text += "s";

		text += " to destroy";

		this.textIndicator.setVisible(true);
		this.textIndicator.setText(text);

	}

	public void concealTextIndicator() {
		this.textIndicator.setVisible(false);
	}

}
