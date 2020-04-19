package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Castellion";
	public NumbersPair DimensionsFrame, DimensionsGapBetweenComponents, DimensionsTile;
	public NumbersPair CoordinatesTextPanel, CoordinatesDeck, CoordinatesDeckSafe;
	public double gapBetweenBorders = 25, textHeight = 50;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsGapBetweenComponents = new NumbersPair(4, 4);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

		x = 150;
		this.DimensionsTile = new NumbersPair(x, x);

		x = 1920;
		y = 6 * this.DimensionsTile.y + 5 * this.DimensionsGapBetweenComponents.y + 2 * this.gapBetweenBorders;
		this.DimensionsFrame = new NumbersPair(x, y);

		x = this.DimensionsFrame.x - this.gapBetweenBorders - this.DimensionsTile.x;
		y = this.gapBetweenBorders;
		this.CoordinatesDeck = new NumbersPair(x, y);

		x = this.CoordinatesDeck.x - this.DimensionsTile.x - this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeck.y;
		this.CoordinatesDeckSafe = new NumbersPair(x, y);

	}

}
