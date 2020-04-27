package gameState;

import controller.DefensiveFormationManager;
import controller.Flow;
import controller.Lists;
import controller.TilesToDestroyIndicator;
import tile.Tile;
import utils.SelectImageViewManager;

public class DestroyTilesDueToTraitor extends AGameState {

	private int tilesToDestroy = 0;

	@Override
	public void handleGameStateChange() {

		this.tilesToDestroy = 4;
		this.tilesToDestroy -= DefensiveFormationManager.INSTANCE.lineOfDefences;

		Lists.INSTANCE.board.setUpTilesCanBeDestroyedByPyro();

		updateTileIndicator();

	}

	@Override
	protected void handleTileBoardPressedNonEmpty(Tile tile) {

		if (!SelectImageViewManager.INSTANCE.isSelectedImageView(tile))
			return;

		this.tilesToDestroy--;
		Lists.INSTANCE.board.listBoard.remove(tile);
		tile.getImageView().setVisible(false);
		Lists.INSTANCE.board.shiftBoard();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		if (this.tilesToDestroy > 0) {
			
			updateTileIndicator();
			Lists.INSTANCE.board.setUpTilesCanBeDestroyedByPyro();
			return;
			
		} else {
			TilesToDestroyIndicator.INSTANCE.concealTextIndicator();
			Flow.INSTANCE.proceed();
		}

	}

	private void updateTileIndicator() {
		TilesToDestroyIndicator.INSTANCE.showTextIndicator(this.tilesToDestroy);
	}

}
