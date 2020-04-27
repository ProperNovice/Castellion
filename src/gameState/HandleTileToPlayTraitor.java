package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import controller.OrdealCardTraitorList;
import enums.EGameState;
import enums.EText;
import tile.TileTraitorBlack;

public class HandleTileToPlayTraitor extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Modifiers.INSTANCE.getTileToPlay() instanceof TileTraitorBlack) {

			EText.PLACE_TRAITOR_TILE_IND.showText();
			EText.CONTINUE.showText();

		} else {

			EText.PLACE_TRAITOR_TILE_OPT.showText();
			EText.DESTROY_TILES.showText();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		if (eText.equals(EText.DESTROY_TILES)) {

			placeTileToDiscardPile();
			Flow.INSTANCE.getFlow().addFirst(EGameState.DESTROY_TILES_DUO_TO_TRAITOR);

		} else
			placeTileOnOrdealCard();

		Flow.INSTANCE.proceed();

	}

	private void placeTileOnOrdealCard() {

		Lists.INSTANCE.drawNormal.getArrayList().removeFirst();
		OrdealCardTraitorList.INSTANCE.addTraitorTileRelocate(Modifiers.INSTANCE.getTileToPlay());

	}

	private void placeTileToDiscardPile() {

		Lists.INSTANCE.drawNormal.getArrayList().removeFirst();
		Lists.INSTANCE.discardPile.getArrayList().addFirst(Modifiers.INSTANCE.getTileToPlay());
		Lists.INSTANCE.discardPile.relocateImageViews();

	}

}
