package utils;

import javafx.scene.text.Font;
import utils.EventHandler.EventHandlerAble;

public class Button implements INode {

	private javafx.scene.control.Button button = null;

	public Button(String text) {

		this.button = new javafx.scene.control.Button(text);
		PlatformFX.runLater(() -> this.button.setFocusTraversable(false));

		ParentInstance.INSTANCE.getParentInstance().addNode(this.button);

	}

	public final void setVisible(final boolean value) {
		PlatformFX.runLater(() -> this.button.setVisible(value));
	}

	@Override
	public final double getLayoutX() {
		return this.button.getLayoutX();
	}

	@Override
	public final double getLayoutY() {
		return this.button.getLayoutY();
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.button.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.button.toFront());
	}

	@Override
	public void relocateTopLeft(final double x, final double y) {
		PlatformFX.runLater(() -> this.button.relocate(x, y));
	}

	@Override
	public void relocateTopLeft(final NumbersPair numbersPair) {
		relocateTopLeft(numbersPair.x, numbersPair.y);
	}

	public void setEventHandler(EventHandlerAble eventHandlerAble) {

		PlatformFX.runLater(() -> {

			EventHandler eventHandler = new EventHandler(eventHandlerAble);

			this.button.setOnMouseEntered(eventHandler);
			this.button.setOnMouseExited(eventHandler);
			this.button.setOnMousePressed(eventHandler);

		});

	}

	public final void setText(String text) {
		PlatformFX.runLater(() -> this.button.setText(text));
	}

	public final void setFont(int value) {
		PlatformFX.runLater(() -> this.button.setFont(new Font(value)));
	}

	public void setSize(double width, double height) {

		PlatformFX.runLater(() -> {

			this.button.setMinSize(width, height);
			this.button.setMaxSize(width, height);

		});

	}

	public final void setHeight(double value) {

		PlatformFX.runLater(() -> {

			this.button.setMinHeight(value);
			this.button.setMaxHeight(value);

		});

	}

	public final void setWidth(double value) {

		PlatformFX.runLater(() -> {

			this.button.setMinWidth(value);
			this.button.setMaxWidth(value);

		});

	}

}
