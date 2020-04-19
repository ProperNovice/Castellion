package tile;

import controller.Credentials;
import controller.Flow;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

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
		Flow.INSTANCE.getCurrentGameState().handleTilePressed(this);
	}

	protected String getBackFileName() {
		return "tiles/Back";
	}

}
