package gameState;

import controller.Lists;
import enums.EText;
import javafx.scene.input.KeyCode;
import tile.Tile;
import tile.TileEmpty;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.Text;

public abstract class AGameState {

	public abstract void handleGameStateChange();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text option executing");
		Logger.INSTANCE.logNewLine(textEnum);

		Text.INSTANCE.concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		int textOptionToHandle = KeyCodeHandler.INSTANCE.getKeyCodeInt(keyCode);

		EText textEnumPressed = Text.INSTANCE.getTextEnumOptionShowing(textOptionToHandle);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void executeTextOption(EText eText) {

	}

	protected final void concealText() {
		Text.INSTANCE.concealText();
	}

	public final void handleTilePressed(Tile tile) {

		if (Lists.INSTANCE.drawSeer.getArrayList().contains(tile))
			handleTileDrawSeerPressed(tile);
		else if (Lists.INSTANCE.deckNormal.getArrayList().contains(tile))
			handleTileDeckNormalPressed(tile);
		else if (Lists.INSTANCE.deckSafe.getArrayList().contains(tile))
			handleTileDeckSafePressed(tile);
		else if (tile instanceof TileEmpty)
			handleTileEmptyPressed(tile);
		else if (Lists.INSTANCE.board.listBoard.contains(tile))
			handleTileBoardPressedNonEmpty(tile);
		else if (Lists.INSTANCE.discardPileChameleon.getArrayList().contains(tile))
			handleTileDiscardPileChameleonPressed(tile);

	}

	protected void handleTileDrawSeerPressed(Tile tile) {

	}

	protected void handleTileDeckNormalPressed(Tile tile) {

	}

	protected void handleTileDeckSafePressed(Tile tile) {

	}

	protected void handleTileEmptyPressed(Tile tile) {

	}

	protected void handleTileBoardPressedNonEmpty(Tile tile) {

	}

	protected void handleTileDiscardPileChameleonPressed(Tile tile) {

	}

}
