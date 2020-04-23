package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;
import tile.Tile;
import utils.Logger;
import utils.Text;

public class HandleTileToPlayDefender extends AGameState {

	@Override
	public void handleGameStateChange() {

		Lists.INSTANCE.board.placeEmptyTilesForPlacingDefenderTile();

		printBoardCredentials();

		if (Lists.INSTANCE.board.listEmptyTiles.size() > 0)
			EText.PLACE_TILE.showText();

		EText.DISCARD_TILE.showText();

	}

	@Override
	protected void handleTileEmptyPressed(Tile tile) {

		Tile tileToAdd = Modifiers.INSTANCE.getTileToPlay();

		Text.INSTANCE.concealText();

		Lists.INSTANCE.board.releaseEmptyTiles();
		Lists.INSTANCE.drawNormal.getArrayList().remove(tileToAdd);
		Lists.INSTANCE.board.listBoard.addLast(tileToAdd);

		Modifiers.INSTANCE.getTileToPlay().getImageView().relocateCenter(tile.getImageView().getCenter());

		Lists.INSTANCE.board.relocateBoard();

		Flow.INSTANCE.proceed();

	}

	@Override
	protected void executeTextOption(EText eText) {
		
		Lists.INSTANCE.board.releaseEmptyTiles();

		Lists.INSTANCE.drawNormal.getArrayList().remove(Modifiers.INSTANCE.getTileToPlay());
		Lists.INSTANCE.discardPile.getArrayList().addFirst(Modifiers.INSTANCE.getTileToPlay());
		Lists.INSTANCE.discardPile.relocateImageViews();

		Flow.INSTANCE.executeGameState(EGameState.HANDLE_TILE_DISCARDED);

	}

	private void printBoardCredentials() {

		Logger.INSTANCE.log("board -> " + Lists.INSTANCE.board.listBoard.size() + " tiles");
		Logger.INSTANCE.log("empty -> " + Lists.INSTANCE.board.listEmptyTiles.size() + " tiles");
		Logger.INSTANCE.newLine();

	}

}
