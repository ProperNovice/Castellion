package gameState;

import cards.CardOrdeal;
import controller.DefensiveFormationManager;
import controller.Flow;
import controller.Lists;
import controller.TilesToDestroyIndicator;
import enums.EGameState;
import enums.EText;
import tile.Tile;
import utils.ArrayList;
import utils.Logger;
import utils.SelectImageViewManager;

public class DestroyTilesDueToOrdealCard extends AGameState {

	private int tilesToDestroy, tilesLeftToDestroy;
	private boolean canChooseTiles = true;
	private ArrayList<Tile> tilesSelected = new ArrayList<Tile>();

	@Override
	public void handleGameStateChange() {

		this.tilesSelected.clear();

		CardOrdeal ordealCard = Lists.INSTANCE.ordealCards.getArrayList().getFirst();
		ordealCard.getImageView().flipFront();
		this.tilesToDestroy = ordealCard.getTilesToDestroy();

		this.tilesToDestroy = 8; // TODO

		Logger.INSTANCE.log("Tiles to destroy start -> " + this.tilesToDestroy);

		this.tilesToDestroy -= DefensiveFormationManager.INSTANCE.bastions * 4;
		this.tilesToDestroy = Math.max(0, this.tilesToDestroy);

		this.tilesLeftToDestroy = this.tilesToDestroy;

		Logger.INSTANCE.logNewLine("Tiles to destroy -> " + this.tilesToDestroy);

		int boardSize = Lists.INSTANCE.board.listBoard.size();
		boardSize -= 6;

		if (boardSize < this.tilesToDestroy)
			Flow.INSTANCE.executeGameState(EGameState.END_GAME_LOSE);

		else if (boardSize == this.tilesToDestroy) {

			EText.TILES_TO_DESTROY.showText();

			for (Tile tile : Lists.INSTANCE.board.listBoard)
				if (!Lists.INSTANCE.board.listFoundation.contains(tile))
					SelectImageViewManager.INSTANCE.addSelectImageView(tile);

			this.canChooseTiles = false;

			EText.CONTINUE.showText();

		} else
			TilesToDestroyIndicator.INSTANCE.showTextIndicator(this.tilesLeftToDestroy);

	}

	@Override
	protected void handleTileBoardPressedNonEmpty(Tile tile) {

		concealText();

		if (!this.canChooseTiles)
			return;

		if (Lists.INSTANCE.board.listFoundation.contains(tile))
			return;

		if (SelectImageViewManager.INSTANCE.isSelectedImageView(tile)) {

			SelectImageViewManager.INSTANCE.releaseSelectImageView(tile);
			this.tilesLeftToDestroy++;
			this.tilesSelected.remove(tile);

		} else if (this.tilesLeftToDestroy == 0) {

			EText.BLANK.showText();
			EText.CONTINUE.showText();
			return;

		} else {

			SelectImageViewManager.INSTANCE.addSelectImageView(tile);
			this.tilesLeftToDestroy--;
			this.tilesSelected.addLast(tile);

		}

		TilesToDestroyIndicator.INSTANCE.showTextIndicator(this.tilesLeftToDestroy);

		if (this.tilesLeftToDestroy > 0)
			return;

		EText.BLANK.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		TilesToDestroyIndicator.INSTANCE.concealTextIndicator();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		concealText();

		for (Tile tile : this.tilesSelected) {

			Lists.INSTANCE.board.listBoard.remove(tile);
			tile.getImageView().setVisible(false);

		}

		Lists.INSTANCE.board.shiftBoard();
		Flow.INSTANCE.proceed();

	}

}
