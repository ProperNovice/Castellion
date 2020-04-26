package cards;

import controller.Credentials;
import enums.ELayerZ;
import enums.EOrdealAbility;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;
import utils.Logger;

public class CardOrdeal implements ImageViewAble, EventHandlerAble {

	private EOrdealAbility eOrdealAbility = null;
	private int ordealAbilityInt, tilesToDestroy;

	public CardOrdeal(EOrdealAbility eOrdealAbility, int ordealAbilityInt, int tilesToDestroy, String fileName) {

		this.eOrdealAbility = eOrdealAbility;
		this.ordealAbilityInt = ordealAbilityInt;
		this.tilesToDestroy = tilesToDestroy;
		createCard(fileName);

	}

	public CardOrdeal(EOrdealAbility eOrdealAbility, int ordealAbilityInt, String fileName) {
		this(eOrdealAbility, ordealAbilityInt, 0, fileName);
	}

	private void createCard(String fileName) {

		String folder = "ordeals/";

		new ImageView(folder + fileName + ".png", this, ELayerZ.ORDEAL_CARDS);
		getImageView().setWidth(Credentials.INSTANCE.DimensionsCard);
		getImageView().setBack(folder + "back.png");
		getImageView().setVisible(false);

	}

	public final void print() {

		String seperator = "**********";

		Logger.INSTANCE.log(seperator);
		Logger.INSTANCE.logNewLine("ordeal card");
		Logger.INSTANCE.log(this.eOrdealAbility);
		Logger.INSTANCE.log(this.ordealAbilityInt);

		if (this.tilesToDestroy > 0)
			Logger.INSTANCE.log("tiles to destroy -> " + this.tilesToDestroy);

		Logger.INSTANCE.logNewLine(seperator);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {

		if (getImageView().isFlippedBack())
			return;

		print();

	}

	public EOrdealAbility getEOrdealAbility() {
		return this.eOrdealAbility;
	}

	public int getOrdealAbilityInt() {
		return this.ordealAbilityInt;
	}

	public int getTilesToDestroy() {
		return this.tilesToDestroy;
	}

}
