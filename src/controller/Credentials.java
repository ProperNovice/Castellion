package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "Castellion";
	public final double SELECT_IMAGEVIEW_WIDTH = 1.0 / 2;
	public NumbersPair DimensionsFrame, DimensionsGapBetweenComponents, DimensionsTile, DimensionsTileAndGap;
	public NumbersPair CoordinatesTextPanel, CoordinatesDeckNormal, CoordinatesDeckSafe, CoordinatesDrawNormal,
			CoordinatesDrawSeer, CoordinatesBoardFirstTile, CoordinatesDiscardPile;
	public double gapBetweenBorders = 25, textHeight = 50;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsGapBetweenComponents = new NumbersPair(2, 2);

		x = 150;
		this.DimensionsTile = new NumbersPair(x, x);

		x = 1920;
		y = 6 * this.DimensionsTile.y + 5 * this.DimensionsGapBetweenComponents.y + 2 * this.gapBetweenBorders;
		this.DimensionsFrame = new NumbersPair(x, y);

		x = this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x;
		y = this.DimensionsTile.y + this.DimensionsGapBetweenComponents.y;
		this.DimensionsTileAndGap = new NumbersPair(x, y);

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.CoordinatesDeckNormal = new NumbersPair(x, y);

		x = this.CoordinatesDeckNormal.x + this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckNormal.y;
		this.CoordinatesDeckSafe = new NumbersPair(x, y);

		x = this.CoordinatesDeckSafe.x + this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckSafe.y;
		this.CoordinatesDrawSeer = new NumbersPair(x, y);

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders + this.DimensionsTileAndGap.y;
		this.CoordinatesDrawNormal = new NumbersPair(x, y);

		x = this.CoordinatesDrawNormal.x + this.DimensionsTileAndGap.x;
		y = this.CoordinatesDrawNormal.y;
		this.CoordinatesTextPanel = new NumbersPair(x, y);

		x = this.DimensionsFrame.x - this.gapBetweenBorders
				- 3 * (this.DimensionsTile.x + this.DimensionsGapBetweenComponents.x) - this.DimensionsTile.x / 2;
		y = this.DimensionsFrame.y - this.gapBetweenBorders - this.DimensionsTile.y / 2;
		this.CoordinatesBoardFirstTile = new NumbersPair(x, y);

		x = this.gapBetweenBorders;
		y = this.DimensionsFrame.y - this.gapBetweenBorders - this.DimensionsTile.y;
		this.CoordinatesDiscardPile = new NumbersPair(x, y);

	}

}
