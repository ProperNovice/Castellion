package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;
import interfaces.IDefenderFactionAble;
import interfaces.IShapeTypeAble;
import tile.Tile;
import tile.TileDefender;
import utils.SelectImageViewManager;
import utils.Text;

public class ResolveChameleon extends AGameState {

	@Override
	public void handleGameStateChange() {

		addTilesToChameleonList();

		for (Tile tile : Lists.INSTANCE.discardPileChameleon)
			SelectImageViewManager.INSTANCE.addSelectImageView(tile);

		if (Lists.INSTANCE.discardPileChameleon.getArrayList().size() == 1)
			recoverTile(Lists.INSTANCE.discardPileChameleon.getArrayList().getFirst());
		else
			EText.CHOOSE_TILE_TO_RECOVER.showText();

	}

	@Override
	protected void handleTileDiscardPileChameleonPressed(Tile tile) {
		recoverTile(tile);
	}

	private void addTilesToChameleonList() {

		IShapeTypeAble iShapeTypeAble = (IShapeTypeAble) Modifiers.INSTANCE.getTileToPlay();
		IDefenderFactionAble iDefenderFactionAble = (IDefenderFactionAble) Modifiers.INSTANCE.getTileToPlay();

		for (Tile tile : Lists.INSTANCE.discardPile.getArrayList().clone()) {

			if (!(tile instanceof TileDefender))
				continue;

			TileDefender tileDefenderTemp = (TileDefender) tile;

			if (!iShapeTypeAble.getShapeType().equals(tileDefenderTemp.getShapeType()))
				continue;

			if (iDefenderFactionAble.getDefenderFaction().equals(tileDefenderTemp.getDefenderFaction()))
				continue;

			boolean canBeAdded = true;

			if (!Lists.INSTANCE.discardPileChameleon.getArrayList().isEmpty())
				for (Tile tileChameleon : Lists.INSTANCE.discardPileChameleon.getArrayList().clone()) {

					TileDefender tileDefenderChameleon = (TileDefender) tileChameleon;

					if (!tileDefenderChameleon.getDefenderFaction().equals(tileDefenderTemp.getDefenderFaction()))
						continue;

					canBeAdded = false;

				}

			if (!canBeAdded)
				continue;

			Lists.INSTANCE.discardPileChameleon.getArrayList().addLast(tileDefenderTemp);
			Lists.INSTANCE.discardPile.getArrayList().remove(tileDefenderTemp);

		}

		Lists.INSTANCE.discardPileChameleon.relocateImageViews();

	}

	private void recoverTile(Tile tile) {

		Text.INSTANCE.concealText();

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Lists.INSTANCE.drawNormal.getArrayList().addLast(tile);
		Lists.INSTANCE.discardPileChameleon.getArrayList().remove(tile);
		Lists.INSTANCE.discardPile.getArrayList().addAll(Lists.INSTANCE.discardPileChameleon.getArrayList());
		Lists.INSTANCE.discardPileChameleon.getArrayList().clear();

		Lists.INSTANCE.drawNormal.relocateImageViews();
		Lists.INSTANCE.discardPile.relocateImageViews();

		Modifiers.INSTANCE.setTileToPlay(tile);
		Flow.INSTANCE.executeGameState(EGameState.HANDLE_TILE_TO_PLAY);

	}

}
