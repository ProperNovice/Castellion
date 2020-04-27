package gameState;

import controller.Flow;
import controller.Lists;
import utils.ImageViewAble;
import utils.MapImageViews;

public class RestartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		for (ImageViewAble imageViewAble : MapImageViews.INSTANCE.getImageViewsMap())
			imageViewAble.getImageView().setVisible(false);

		Lists.INSTANCE.startGame();
		Flow.INSTANCE.getFlow().clear();
		Flow.INSTANCE.proceed();

	}

}
