package utils;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle implements INode {

	private javafx.scene.shape.Circle circle = null;
	private double topLeftX = 0, topLeftY = 0;
	private double radius;

	public Circle(double radius, Parent parent) {

		PlatformFX.runLater(() -> {

			this.radius = radius;
			this.circle = new javafx.scene.shape.Circle(this.radius);
			this.circle.setFill(null);
			this.circle.setStroke(Color.BLACK);

			parent.addNode(this.circle);

		});

	}

	public Circle(double radius) {

		PlatformFX.runLater(() -> {

			this.radius = radius;
			this.circle = new javafx.scene.shape.Circle(this.radius);
			this.circle.setFill(null);
			this.circle.setStroke(Color.BLACK);

			ParentInstance.INSTANCE.getParentInstance().addNode(this.circle);

		});

	}

	public boolean contains(double localX, double localY) {

		double centerX = this.topLeftX + this.radius;
		double centerY = this.topLeftY + this.radius;
		double differenceX = localX - centerX;
		double differenceY = localY - centerY;
		double distance = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

		return (distance <= this.radius);

	}

	@Override
	public void relocateTopLeft(double x, double y) {
		this.topLeftX = x;
		this.topLeftY = y;
		relocate();
	}

	@Override
	public void relocateTopLeft(final NumbersPair numbersPair) {
		relocateTopLeft(numbersPair.x, numbersPair.y);
	}

	public void relocateCenter(double x, double y) {
		this.topLeftX = x - this.radius;
		this.topLeftY = y - this.radius;
		relocate();
	}

	private void relocate() {
		PlatformFX.runLater(() -> this.circle.relocate(this.topLeftX, this.topLeftY));
	}

	public final void setFill(Paint value) {
		PlatformFX.runLater(() -> this.circle.setFill(value));
	}

	public final void setStroke(Paint value) {
		PlatformFX.runLater(() -> this.circle.setStroke(value));
	}

	public final void setOnMouseEntered(EventHandler<? super MouseEvent> value) {
		PlatformFX.runLater(() -> this.circle.setOnMouseEntered(value));
	}

	public final void setOnMouseExited(EventHandler<? super MouseEvent> value) {
		PlatformFX.runLater(() -> this.circle.setOnMouseExited(value));
	}

	public final void setOnMousePressed(EventHandler<? super MouseEvent> value) {
		PlatformFX.runLater(() -> this.circle.setOnMousePressed(value));
	}

	public final void setRadius(double radius) {

		PlatformFX.runLater(() -> {

			double centerX = this.topLeftX + this.radius;
			double centerY = this.topLeftY + this.radius;

			this.radius = radius;

			this.topLeftX = centerX - this.radius;
			this.topLeftY = centerY - this.radius;

			this.circle.setRadius(radius);
			relocate();

		});

	}

	public final void setVisible(boolean value) {
		PlatformFX.runLater(() -> this.circle.setVisible(value));
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.circle.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.circle.toFront());
	}

	public double getLayoutX() {
		return this.topLeftX;
	}

	public double getLayoutY() {
		return this.topLeftY;
	}

	public double getCenterX() {
		return this.topLeftX + this.radius;
	}

	public double getCenterY() {
		return this.topLeftY + this.radius;
	}

	public double getRadius() {
		return this.radius;
	}

}
