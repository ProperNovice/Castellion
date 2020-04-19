package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "Castellion";
	public NumbersPair DimensionsFrame, DimensionsGapBetweenComponents, DimensionsTile;
	public NumbersPair CoordinatesTextPanel, CoordinatesDeckNormal, CoordinatesDeckSafe, CoordinatesDrawNormal,
			CoordinatesDrawSeer;
	public double gapBetweenBorders = 25, textHeight = 50;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsGapBetweenComponents = new NumbersPair(4, 4);

		x = 150;
		this.DimensionsTile = new NumbersPair(x, x);

		x = 1920;
		y = 6 * this.DimensionsTile.y + 5 * this.DimensionsGapBetweenComponents.y + 2 * this.gapBetweenBorders;
		this.DimensionsFrame = new NumbersPair(x, y);

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.CoordinatesDrawNormal = new NumbersPair(x, y);

		x = this.CoordinatesDrawNormal.x + this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDrawNormal.y;
		this.CoordinatesDeckNormal = new NumbersPair(x, y);

		x = this.CoordinatesDeckNormal.x + this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckNormal.y;
		this.CoordinatesDeckSafe = new NumbersPair(x, y);

		x = this.CoordinatesDeckSafe.x + this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckNormal.y;
		this.CoordinatesDrawSeer = new NumbersPair(x, y);

		x = this.CoordinatesDrawNormal.x;
		y = this.CoordinatesDrawNormal.y + this.DimensionsTile.y + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesTextPanel = new NumbersPair(x, y);

	}

}
