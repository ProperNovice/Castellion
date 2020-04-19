package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EText;
import tile.Tile;
import utils.Text;

public class DrawPhase extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Lists.INSTANCE.drawSeer.getArrayList().size() == 1)
			addTileToDrawNormalProceed(Lists.INSTANCE.drawSeer.getArrayList().removeFirst());

		else if (!Lists.INSTANCE.drawSeer.getArrayList().isEmpty())
			EText.CHOOSE_A_SEER_TILE.showText();

		else if (Lists.INSTANCE.deckSafe.getArrayList().isEmpty())
			addTileToDrawNormalProceed(Lists.INSTANCE.drawNormal.getArrayList().removeFirst());

		else {

			EText.DRAW_TILE_FROM_PILE.showText();
			EText.NORMAL.showText();
			EText.SAFE.showText();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		if (eText.equals(EText.NORMAL))
			handleTileDeckNormalPressed(Lists.INSTANCE.deckNormal.getArrayList().getFirst());
		else if (eText.equals(EText.SAFE))
			handleTileDeckNormalPressed(Lists.INSTANCE.deckSafe.getArrayList().getFirst());

	}

	@Override
	protected void handleTileDrawSeerPressed(Tile tile) {
		Lists.INSTANCE.drawSeer.getArrayList().remove(tile);
		Lists.INSTANCE.drawSeer.relocateImageViews();
		addTileToDrawNormalProceed(tile);
	}

	@Override
	protected void handleTileDeckNormalPressed(Tile tile) {
		Lists.INSTANCE.deckNormal.getArrayList().remove(tile);
		addTileToDrawNormalProceed(tile);
	}

	@Override
	protected void handleTileDeckSafePressed(Tile tile) {
		Lists.INSTANCE.deckSafe.getArrayList().remove(tile);
		addTileToDrawNormalProceed(tile);
	}

	private void addTileToDrawNormalProceed(Tile tile) {

		Text.INSTANCE.concealText();

		tile.getImageView().flipFront();
		Lists.INSTANCE.drawNormal.getArrayList().addLast(tile);
		Lists.INSTANCE.drawNormal.relocateImageViews();

		Modifiers.INSTANCE.setTileToPlay(tile);
		Flow.INSTANCE.proceed();

	}

}
