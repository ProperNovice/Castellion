package utils;

import controller.Credentials;

public enum LineCast {

	INSTANCE;

	private double startingX, startingY, endingX, endingY, stepX, stepY;
	private ArrayList<ImageViewAble> listLineCast = new ArrayList<ImageViewAble>();
	private ArrayList<ImageViewAble> listStarting = new ArrayList<ImageViewAble>();

	public <T> ArrayList<T> lineCastList(ImageViewAble imageViewAble, DirectionEnum directionEnum, int units) {

		ImageView imageView = imageViewAble.getImageView();

		double startingX = imageView.getCenterX();
		double startingY = imageView.getCenterY();
		double width = imageView.getWidth();
		double height = imageView.getHeight();
		NumbersPair dimensions = new NumbersPair(width, height);

		return lineCastList(startingX, startingY, dimensions, directionEnum, units);

	}

	@SuppressWarnings("unchecked")
	public <T> Object lineCastSingle(ImageViewAble imageViewAble, DirectionEnum directionEnum, int units) {

		lineCastList(imageViewAble, directionEnum, units);

		if (this.listLineCast.isEmpty())
			return null;
		else
			return (T) this.listLineCast.getFirst();

	}

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> lineCastList(double startingX, double startingY, NumbersPair dimensions,
			DirectionEnum directionEnum, int units) {

		this.startingX = startingX;
		this.startingY = startingY;

		setUpCredentials(startingX, startingY, dimensions, directionEnum, units);

		executeLineCast();
		return (ArrayList<T>) this.listLineCast;

	}

	@SuppressWarnings("unchecked")
	public <T> Object lineCastSingle(double startingX, double startingY, NumbersPair dimensions,
			DirectionEnum directionEnum, int units) {

		lineCastList(startingX, startingY, dimensions, directionEnum, units);

		if (this.listLineCast.isEmpty())
			return null;
		else
			return (T) this.listLineCast.getFirst();

	}

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> lineCast(double startingX, double startingY, double endingX, double endingY) {

		this.startingX = startingX;
		this.startingY = startingY;
		this.endingX = endingX;
		this.endingY = endingY;

		executeLineCast();
		return (ArrayList<T>) this.listLineCast;

	}

	@SuppressWarnings("unchecked")
	public <T> T objectAtCoordinates(double x, double y) {

		ArrayList<ImageViewAble> list = getImageViewAbleContaining(x, y);

		if (list.isEmpty())
			return null;
		else
			return (T) list.getFirst();

	}

	public <T> T objectAtCoordinates(NumbersPair numbersPair) {
		return objectAtCoordinates(numbersPair.x, numbersPair.y);
	}

	private void setUpCredentials(double startingX, double startingY, NumbersPair dimensions,
			DirectionEnum directionEnum, int units) {

		this.startingX = startingX;
		this.startingY = startingY;
		this.endingX = this.startingX;
		this.endingY = this.startingY;

		double width = units * (dimensions.x + Credentials.INSTANCE.DimensionsGapBetweenComponents.x);
		double height = units * (dimensions.y + Credentials.INSTANCE.DimensionsGapBetweenComponents.x);

		switch (directionEnum) {

		case DOWN:
			this.endingY += height;
			break;

		case LEFT:
			this.endingX -= width;
			break;

		case RIGHT:
			this.endingX += width;
			break;

		case UP:
			this.endingY -= height;
			break;

		}

	}

	private void executeLineCast() {

		this.listLineCast.clear();
		this.listStarting.clear();

		setStepAxis();
		setListStartingImageViewAble();
		setListLinecast();

	}

	private void setStepAxis() {
		setStepX();
		setStepY();
	}

	private void setStepX() {

		if (this.endingX > this.startingX)
			this.stepX = 1;
		else if (this.endingX < this.startingX)
			this.stepX = -1;
		else
			this.stepX = 0;

	}

	private void setStepY() {

		double dX = Math.abs(this.startingX - this.endingX);
		double dY = Math.abs(this.startingY - this.endingY);

		if (dX == 0)
			this.stepY = 1;
		else
			this.stepY = dY / dX;

		if (this.endingY < this.startingY)
			this.stepY = -this.stepY;

	}

	private void setListStartingImageViewAble() {
		this.listStarting.addAll(getImageViewAbleContaining(this.startingX, this.startingY));
	}

	private void setListLinecast() {

		double currentX = this.startingX;
		double currentY = this.startingY;

		do {

			currentX += this.stepX;
			currentY += this.stepY;

			ArrayList<ImageViewAble> list = getImageViewAbleContaining(currentX, currentY);

			if (list.isEmpty())
				continue;

			for (ImageViewAble imageViewAble : list) {

				if (this.listStarting.contains(imageViewAble))
					continue;

				if (this.listLineCast.contains(imageViewAble))
					continue;

				this.listLineCast.addLast(imageViewAble);

			}

		} while (currentX != this.endingX || currentY != this.endingY);

	}

	private ArrayList<ImageViewAble> getImageViewAbleContaining(double x, double y) {

		ArrayList<ImageViewAble> list = new ArrayList<ImageViewAble>();

		for (ImageViewAble imageViewAble : MapImageViews.INSTANCE.getImageViewsMap()) {

			if (imageViewAble instanceof SelectImageView)
				continue;

			ImageView imageView = MapImageViews.INSTANCE.getImageViewsMap().getValue(imageViewAble);

			if (!imageView.isVisible())
				continue;

			if (!imageView.contains(x, y))
				continue;

			list.addLast(imageViewAble);

		}

		return list;

	}

}
