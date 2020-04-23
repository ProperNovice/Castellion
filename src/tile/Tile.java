package tile;

import controller.Credentials;
import controller.Flow;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;
import utils.Logger;

public abstract class Tile implements ImageViewAble, EventHandlerAble {

	protected final void createTile(String fileName) {

		String filePath = "tiles/";
		filePath += fileName;
		filePath += ".jpg";

		new ImageView(filePath, this);
		getImageView().setWidth(Credentials.INSTANCE.DimensionsTile);
		getImageView().setBack(this.getBackFileName() + ".jpg");
		getImageView().flip();

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		printTile();
		Flow.INSTANCE.getCurrentGameState().handleTilePressed(this);
	}

	public final void printTile() {

		String string = "************";

		Logger.INSTANCE.log(string);
		Logger.INSTANCE.log("printing tile");
		Logger.INSTANCE.log(this.getClass());
		printCredentials();
		Logger.INSTANCE.logNewLine(string);

	}

	protected void printCredentials() {

	}

	protected String getBackFileName() {
		return "tiles/Back";
	}

}
