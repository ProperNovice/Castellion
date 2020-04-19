package utils;

import controller.Credentials;
import enums.EText;
import enums.EText.TextTypeEnum;

public enum Text {

	INSTANCE;

	private ArrayList<TextGame> textGame = new ArrayList<>();
	private ArrayList<TextGame> textGameShowing = new ArrayList<>();
	private Coordinates coordinates = null;

	private Text() {

		createCoordinates();
		createTexts();

	}

	private void createCoordinates() {

		this.coordinates = new CoordinatesBuilder().height(Credentials.INSTANCE.textHeight)
				.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesTextPanel).objectsPerRow(1)
				.rearrangeTypeEnum(RearrangeTypeEnum.LINEAR).gapY(0).build();

	}

	private void createTexts() {

		for (EText eText : EText.values())
			this.textGame.addLast(new TextGame(eText));

	}

	public void showText(EText eText) {

		for (TextGame textGame : this.textGame) {

			if (!textGame.getTextEnum().equals(eText))
				continue;

			this.textGameShowing.addLast(textGame);
			break;

		}

		showText();

	}

	public void showText(EText... eText) {

		for (EText eTextTemp : eText)
			for (TextGame textGame : this.textGame) {

				if (!textGame.getTextEnum().equals(eTextTemp))
					continue;

				this.textGameShowing.addLast(textGame);
				break;

			}

		showText();

	}

	public void showText(ArrayList<EText> eText) {

		for (EText eTextTemp : eText)
			for (TextGame textGame : this.textGame) {

				if (!textGame.getTextEnum().equals(eTextTemp))
					continue;

				this.textGameShowing.addLast(textGame);
				break;

			}

		showText();

	}

	private void showText() {

		for (TextGame textGame : this.textGameShowing) {

			textGame.toFront();

			textGame.setVisible(true);
			textGame.relocate(this.coordinates.getCoordinate(this.textGameShowing.indexOf(textGame)));

		}

	}

	public void concealText() {

		for (TextGame textGame : this.textGameShowing)
			textGame.setVisible(false);

		this.textGameShowing.clear();

	}

	public EText getTextEnumOptionShowing(int textOptionListOrder) {

		int textOptionId = 0;
		EText eText = null;

		for (TextGame textGame : this.textGameShowing) {

			EText eTextTemp = textGame.getTextEnum();

			if (eTextTemp.getTextTypeEnum() == TextTypeEnum.INDICATOR)
				continue;

			textOptionId++;

			if (textOptionListOrder != textOptionId)
				continue;

			eText = eTextTemp;
			break;

		}

		return eText;

	}

}
