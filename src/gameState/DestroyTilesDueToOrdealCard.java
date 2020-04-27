package gameState;

import cards.CardOrdeal;
import controller.DefensiveFormationManager;
import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import tile.Tile;
import utils.Logger;
import utils.SelectImageViewManager;

public class DestroyTilesDueToOrdealCard extends AGameState {

	private int tilesToDestroy;
	private boolean canChooseTiles = true;

	@Override
	public void handleGameStateChange() {

		CardOrdeal ordealCard = Lists.INSTANCE.ordealCards.getArrayList().getFirst();
		ordealCard.getImageView().flipFront();
		this.tilesToDestroy = ordealCard.getTilesToDestroy();

		this.tilesToDestroy = 4; // TODO

		Logger.INSTANCE.log("Tiles to destroy start -> " + this.tilesToDestroy);

		this.tilesToDestroy -= DefensiveFormationManager.INSTANCE.bastions * 4;
		this.tilesToDestroy = Math.max(0, this.tilesToDestroy);

		Logger.INSTANCE.logNewLine("Tiles to destroy -> " + this.tilesToDestroy);

		int boardSize = Lists.INSTANCE.board.listBoard.size();
		boardSize -= 6;

		if (boardSize < this.tilesToDestroy)
			Flow.INSTANCE.executeGameState(EGameState.END_GAME_LOSE);

		else if (boardSize == this.tilesToDestroy) {

			for (Tile tile : Lists.INSTANCE.board.listBoard)
				if (!Lists.INSTANCE.board.listFoundation.contains(tile))
					SelectImageViewManager.INSTANCE.addSelectImageView(tile);

			this.canChooseTiles = false;

		}

		EText.CHOOSE_TILES_TO_DESTROY.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void handleTileBoardPressedNonEmpty(Tile tile) {

		if (!this.canChooseTiles)
			return;

		if (Lists.INSTANCE.board.listFoundation.contains(tile))
			return;

		if (SelectImageViewManager.INSTANCE.isSelectedImageView(tile))
			SelectImageViewManager.INSTANCE.releaseSelectImageView(tile);
		else
			SelectImageViewManager.INSTANCE.addSelectImageView(tile);

	}

}
