package utils;

import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;

public class SelectImageView implements ImageViewAble, EventHandlerAble {

	public SelectImageView() {

		new ImageView("misc/select.png", this, ELayerZ.SELECT);
		this.getImageView().setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		SelectImageViewManager.INSTANCE.handleMouseButtonPressedPrimary(this);
	}

}
