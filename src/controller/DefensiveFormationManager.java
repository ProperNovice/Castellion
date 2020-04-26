package controller;

import utils.TextIndicator;

public enum DefensiveFormationManager {

	INSTANCE;

	private TextIndicator bastionTextIndicator = new TextIndicator();
	private TextIndicator lineOfDefenceTextIndicator = new TextIndicator();
	private TextIndicator towerTextIndicator = new TextIndicator();
	public int bastions, lineOfDefences, towers;

	private DefensiveFormationManager() {

		this.bastionTextIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.lineOfDefenceTextIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.towerTextIndicator.setHeight(Credentials.INSTANCE.textHeight);

		double x = Credentials.INSTANCE.CoordinatesDefensiveFormationTexts.x;
		double y = Credentials.INSTANCE.CoordinatesDefensiveFormationTexts.y;

		this.bastionTextIndicator.relocateTopLeft(x, y);

		y += Credentials.INSTANCE.textHeight + Credentials.INSTANCE.DimensionsGapBetweenComponents.y;
		this.lineOfDefenceTextIndicator.relocateTopLeft(x, y);

		y += Credentials.INSTANCE.textHeight + Credentials.INSTANCE.DimensionsGapBetweenComponents.y;
		this.towerTextIndicator.relocateTopLeft(x, y);

	}

	public void updateTexts() {

		this.bastionTextIndicator.setVisible(false);
		this.lineOfDefenceTextIndicator.setVisible(false);
		this.towerTextIndicator.setVisible(false);

		this.bastions = Lists.INSTANCE.board.getBastions();
		this.lineOfDefences = Lists.INSTANCE.board.getLines();
		this.towers = Lists.INSTANCE.board.getTowers();

		if (this.bastions > 0)
			updateBastion();

		if (this.lineOfDefences > 0)
			updateLineOfDefence();

		if (this.towers > 0)
			updateTower();

	}

	private void updateBastion() {
		this.bastionTextIndicator.setText("Bastions: " + this.bastions);
	}

	private void updateLineOfDefence() {
		this.lineOfDefenceTextIndicator.setText("Lines: " + this.lineOfDefences);
	}

	private void updateTower() {
		this.towerTextIndicator.setText("Towers: " + this.towers);
	}

}
