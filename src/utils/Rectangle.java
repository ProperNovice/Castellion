package utils;

import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import utils.EventHandler.EventHandlerAble;

public class Rectangle implements INode {

	private javafx.scene.shape.Polyline rectangle = new javafx.scene.shape.Polyline();
	private double topLeftX = 0, topLeftY = 0;
	private double dimensionX, dimensionY;

	public Rectangle(double x) {

		this.dimensionX = x;
		this.dimensionY = x;

		createRectangle();

	}

	public Rectangle(double x, double y) {

		this.dimensionX = x;
		this.dimensionY = y;

		createRectangle();

	}

	private void createRectangle() {

		PlatformFX.runLater(() -> {

			this.rectangle.getPoints().addAll(0.0, 0.0, this.dimensionX, 0.0, this.dimensionX, this.dimensionY, 0.0,
					this.dimensionY, 0.0, 0.0);

			ParentInstance.INSTANCE.getParentInstance().addNode(this.rectangle);
			this.rectangle.setFill(null);
			this.rectangle.setStroke(Color.BLACK);

		});

	}

	public void setEventHandler(EventHandlerAble eventHandlerAble) {

		PlatformFX.runLater(() -> {

			EventHandler eventHandler = new EventHandler(eventHandlerAble);

			this.rectangle.setOnMouseEntered(eventHandler);
			this.rectangle.setOnMouseExited(eventHandler);
			this.rectangle.setOnMousePressed(eventHandler);

		});

	}

	public final ObservableList<Double> getPoints() {
		return this.rectangle.getPoints();
	}

	public boolean contains(double localX, double localY) {

		if (localX < this.topLeftX)
			return false;

		if (localX > this.topLeftX + this.dimensionX)
			return false;

		if (localY < this.topLeftY)
			return false;

		if (localY > this.topLeftY + this.dimensionY)
			return false;

		return true;

	}

	@Override
	public void relocateTopLeft(double x, double y) {

		this.topLeftX = x;
		this.topLeftY = y;
		PlatformFX.runLater(() -> this.rectangle.relocate(x, y));

	}

	@Override
	public void relocateTopLeft(final NumbersPair numbersPair) {
		relocateTopLeft(numbersPair.x, numbersPair.y);
	}

	public void setWidth(double width) {

		this.dimensionX = width;
		resizeRectangle();

	}

	public void setHeight(double height) {

		this.dimensionY = height;
		resizeRectangle();

	}

	private void resizeRectangle() {

		PlatformFX.runLater(() -> {
			this.rectangle.getPoints().clear();
			this.rectangle.getPoints().addAll(0.0, 0.0, getWidth(), 0.0, getWidth(), getHeight(), 0.0, getHeight(), 0.0,
					0.0);
		});

	}

	public double getWidth() {
		return this.dimensionX;
	}

	public double getHeight() {
		return this.dimensionY;
	}

	public final void setFill(Paint value) {
		PlatformFX.runLater(() -> this.rectangle.setFill(value));
	}

	public final void setStroke(Paint value) {
		PlatformFX.runLater(() -> this.rectangle.setStroke(value));
	}

	public final void setOnMouseEntered(javafx.event.EventHandler<? super MouseEvent> value) {
		PlatformFX.runLater(() -> this.rectangle.setOnMouseEntered(value));
	}

	public final void setOnMouseExited(javafx.event.EventHandler<? super MouseEvent> value) {
		PlatformFX.runLater(() -> this.rectangle.setOnMouseExited(value));
	}

	public final void setOnMousePressed(javafx.event.EventHandler<? super MouseEvent> value) {
		PlatformFX.runLater(() -> this.rectangle.setOnMousePressed(value));
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.rectangle.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.rectangle.toFront());
	}

	public final void setVisible(boolean visibility) {
		PlatformFX.runLater(() -> this.rectangle.setVisible(visibility));
	}

	@Override
	public double getLayoutX() {
		return this.rectangle.getLayoutX();
	}

	@Override
	public double getLayoutY() {
		return this.rectangle.getLayoutY();
	}

}
