package gameState;

import controller.Credentials;
import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import tile.Tile;
import utils.ArrayList;
import utils.DirectionEnum;
import utils.LineCast;
import utils.SelectImageViewManager;

public class ResolvePyroDestroy extends AGameState {

	@Override
	public void handleGameStateChange() {

		Lists.INSTANCE.board.setUpTilesCanBeDestroyedByPyro();
		EText.CHOOSE_TILE_TO_DESTROY.showText();

	}

	@Override
	protected void handleTileBoardPressedNonEmpty(Tile tile) {

		if (!SelectImageViewManager.INSTANCE.hasSelectImageView(tile))
			return;

		concealText();
		
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		tile.getImageView().setVisible(false);
		Lists.INSTANCE.board.listBoard.remove(tile);

		ArrayList<Tile> tiles = LineCast.INSTANCE.lineCastList(tile, DirectionEnum.UP, 5);

		for (Tile tileTemp : tiles) {

			double centerX = tileTemp.getImageView().getCenterX();
			double centerY = tileTemp.getImageView().getCenterY();
			centerY += Credentials.INSTANCE.DimensionsTileAndGap.y;
			tileTemp.getImageView().relocateCenter(centerX, centerY);

		}
		
		Lists.INSTANCE.board.relocateBoard();
		
		Flow.INSTANCE.executeGameState(EGameState.RESOLVE_PYRO_MOVE);

	}

}
