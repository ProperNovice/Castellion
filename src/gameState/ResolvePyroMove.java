package gameState;

import controller.Flow;
import controller.Lists;
import enums.EText;
import tile.Tile;
import utils.SelectImageViewManager;

public class ResolvePyroMove extends AGameState {

	private Tile tileHasBeenSelected = null;

	@Override
	public void handleGameStateChange() {

		if (Lists.INSTANCE.board.listBoard.size() <= 1) {
			Flow.INSTANCE.proceed();
			return;
		}

		this.tileHasBeenSelected = null;
		Lists.INSTANCE.board.setUpTilesCanBeMovedByPyro();
		EText.CHOOSE_TILE_TO_MOVE.showText();

	}

	@Override
	protected void handleTileBoardPressedNonEmpty(Tile tile) {

		if (!SelectImageViewManager.INSTANCE.hasSelectImageView(tile))
			return;

		if (this.tileHasBeenSelected != null)
			return;

		this.tileHasBeenSelected = tile;

		concealText();

		EText.CHOOSE_PLACE_TO_MOVE_TO.showText();

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		SelectImageViewManager.INSTANCE.addSelectImageView(tile);
		Lists.INSTANCE.board.setUpEmptyTilesToMoveByPyro(tile);

	}

	@Override
	protected void handleTileEmptyPressed(Tile tile) {

		concealText();

		Lists.INSTANCE.board.releaseEmptyTiles();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		this.tileHasBeenSelected.getImageView().relocateCenter(tile.getImageView().getCenter());
		Lists.INSTANCE.board.relocateBoard();
		
		Flow.INSTANCE.proceed();

	}

}
