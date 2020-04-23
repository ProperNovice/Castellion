package gameState;

import controller.Flow;
import controller.Lists;
import enums.EText;
import tile.Tile;
import utils.Logger;
import utils.Text;

public class ResolveSeer extends AGameState {

	@Override
	public void handleGameStateChange() {

		int cardsToDraw = Lists.INSTANCE.drawSeer.getArrayList().getCapacity()
				- Lists.INSTANCE.drawSeer.getArrayList().size();

		Logger.INSTANCE.log("to draw -> " + cardsToDraw);
		Logger.INSTANCE.log("normal remaining -> " + Lists.INSTANCE.deckNormal.getArrayList().size());
		Logger.INSTANCE.log("safe remaining -> " + Lists.INSTANCE.deckSafe.getArrayList().size());
		Logger.INSTANCE.newLine();

		if (Lists.INSTANCE.deckNormal.getArrayList().size()
				+ Lists.INSTANCE.deckSafe.getArrayList().size() <= cardsToDraw) {

			Logger.INSTANCE.logNewLine("drawing cards auto");

			Lists.INSTANCE.drawSeer.getArrayList().addAll(Lists.INSTANCE.deckNormal.getArrayList());
			Lists.INSTANCE.drawSeer.getArrayList().addAll(Lists.INSTANCE.deckSafe.getArrayList());
			Lists.INSTANCE.drawSeer.relocateImageViews();

			Lists.INSTANCE.deckNormal.getArrayList().clear();
			Lists.INSTANCE.deckSafe.getArrayList().clear();

		} else if (Lists.INSTANCE.deckNormal.getArrayList().isEmpty()) {

			while (!Lists.INSTANCE.drawSeer.getArrayList().isMaxedCapacity())
				Lists.INSTANCE.drawSeer.getArrayList().addLast(Lists.INSTANCE.deckSafe.getArrayList().removeFirst());

		} else if (Lists.INSTANCE.deckSafe.getArrayList().isEmpty()) {

			while (!Lists.INSTANCE.drawSeer.getArrayList().isMaxedCapacity())
				Lists.INSTANCE.drawSeer.getArrayList().addLast(Lists.INSTANCE.deckNormal.getArrayList().removeFirst());

		}

		for (Tile tile : Lists.INSTANCE.drawSeer)
			tile.getImageView().flipFront();

		Lists.INSTANCE.drawSeer.relocateImageViews();

		if (Lists.INSTANCE.drawSeer.getArrayList().isMaxedCapacity())
			Flow.INSTANCE.proceed();

		else if (Lists.INSTANCE.deckNormal.getArrayList().size() + Lists.INSTANCE.deckSafe.getArrayList().size() == 0)
			Flow.INSTANCE.proceed();

		else
			EText.CHOOSE_TILE_PILE_TO_ADD_FROM.showText();

	}

	@Override
	protected void handleTileDeckNormalPressed(Tile tile) {
		Lists.INSTANCE.deckNormal.getArrayList().remove(tile);
		addTileToDrawSeerProceed(tile);
	}

	@Override
	protected void handleTileDeckSafePressed(Tile tile) {
		Lists.INSTANCE.deckSafe.getArrayList().remove(tile);
		addTileToDrawSeerProceed(tile);

	}

	private void addTileToDrawSeerProceed(Tile tile) {

		Text.INSTANCE.concealText();

		Lists.INSTANCE.drawSeer.getArrayList().addLast(tile);

		for (Tile tileTemp : Lists.INSTANCE.drawSeer)
			tileTemp.getImageView().flipFront();

		Lists.INSTANCE.drawSeer.relocateImageViews();

		handleGameStateChange();

	}

}
