package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Castellion";
	public NumbersPair DimensionsFrame, DimensionsGapBetweenComponents;
	public NumbersPair CoordinatesTextPanel;
	public double gapBetweenBorders = 25, textHeight = 50;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1920, 1080);
		this.DimensionsGapBetweenComponents = new NumbersPair(4, 4);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

	}

}
