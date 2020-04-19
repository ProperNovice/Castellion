package utils;

import controller.Credentials;

public class CoordinatesBuilder {

	private double x = 0, y = 0, width = 0, height = 0, gapX = Credentials.INSTANCE.DimensionsGapBetweenComponents.x,
			gapY = Credentials.INSTANCE.DimensionsGapBetweenComponents.y;
	private int objectsPerRow = -1;
	private RearrangeTypeEnum rearrangeTypeEnum = RearrangeTypeEnum.LINEAR;
	private DirectionEnum directionEnumHorizontal = DirectionEnum.RIGHT, directionEnumVertical = DirectionEnum.DOWN;

	public CoordinatesBuilder() {

	}

	public CoordinatesBuilder x(double x) {
		this.x = x;
		return this;
	}

	public CoordinatesBuilder y(double y) {
		this.y = y;
		return this;
	}

	public CoordinatesBuilder coordinatesNumbersPair(NumbersPair numbersPair) {

		this.x = numbersPair.x;
		this.y = numbersPair.y;
		return this;

	}

	public CoordinatesBuilder width(double width) {
		this.width = width;
		return this;
	}

	public CoordinatesBuilder height(double height) {
		this.height = height;
		return this;
	}

	public CoordinatesBuilder dimensionsNumbersPair(NumbersPair numbersPair) {

		this.width = numbersPair.x;
		this.height = numbersPair.y;
		return this;

	}

	public CoordinatesBuilder gapX(double gapX) {
		this.gapX = gapX;
		return this;
	}

	public CoordinatesBuilder gapY(double gapY) {
		this.gapY = gapY;
		return this;
	}

	public CoordinatesBuilder gapNumbersPair(NumbersPair numbersPair) {
		this.gapX = numbersPair.x;
		this.gapY = numbersPair.y;
		return this;
	}

	public CoordinatesBuilder objectsPerRow(int objectsPerRow) {
		this.objectsPerRow = objectsPerRow;
		return this;
	}

	public CoordinatesBuilder rearrangeTypeEnum(RearrangeTypeEnum rearrangeTypeEnum) {
		this.rearrangeTypeEnum = rearrangeTypeEnum;
		return this;
	}

	public CoordinatesBuilder directionEnumHorizontal(DirectionEnum directionEnumHorizontal) {
		this.directionEnumHorizontal = directionEnumHorizontal;
		return this;
	}

	public CoordinatesBuilder directionEnumVertical(DirectionEnum directionEnumVertical) {
		this.directionEnumVertical = directionEnumVertical;
		return this;
	}

	public Coordinates build() {
		return new Coordinates(this.x, this.y, width, this.height, this.gapX, this.gapY, this.objectsPerRow,
				this.rearrangeTypeEnum, this.directionEnumHorizontal, this.directionEnumVertical);
	}

}
