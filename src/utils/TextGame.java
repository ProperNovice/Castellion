package utils;

import controller.Credentials;
import controller.Flow;
import enums.EText;
import utils.EventHandler.EventHandlerAble;

public class TextGame implements EventHandlerAble {

	private EText textEnum = null;
	private TextIndicator text = null;

	public TextGame(EText eText) {

		this.textEnum = eText;
		createText();

	}

	private void createText() {

		String text = this.textEnum.getString();

		switch (this.textEnum.getTextTypeEnum()) {

		case INDICATOR:
			this.text = new TextIndicator(text);
			break;

		case OPTION:
			this.text = new TextOption(text, this);
			break;

		}

		if (this.textEnum.getString().contains("\n"))
			this.text.setHeight(2 * Credentials.INSTANCE.textHeight);
		else
			this.text.setHeight(Credentials.INSTANCE.textHeight);

		this.text.setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getCurrentGameState().handleTextOptionPressed(this.textEnum);
	}

	public void relocate(double x, double y) {
		this.text.relocateTopLeft(x, y);
	}

	public void relocate(NumbersPair numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public void toFront() {
		this.text.toFront();
	}

	public void setVisible(boolean value) {
		this.text.setVisible(value);
	}

	public EText getTextEnum() {
		return this.textEnum;
	}

}
