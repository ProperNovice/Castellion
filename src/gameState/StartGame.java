package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EDefenderFaction;
import enums.EGameState;
import enums.EShapeType;
import tile.Tile;
import tile.TileDefender;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

//		addTiletoListSeer(new TileDefender(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE));
//		addTiletoListSeer(new TileTraitorNormal());
//		addTiletoListSeer(new TileDefender(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE));
//		addTiletoListSeer(new TileTraitorBlack());

		addTileToDrawNormalProceed(new TileDefender(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE));

		Flow.INSTANCE.getFlow().addFirst(EGameState.HANDLE_TILE_TO_PLAY);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.DRAW_PHASE);

		Flow.INSTANCE.proceed();

	}

	protected void addTiletoListSeer(Tile tile) {

		tile.getImageView().flipFront();
		Lists.INSTANCE.drawSeer.getArrayList().addLast(tile);
		Lists.INSTANCE.drawSeer.relocateImageViews();

	}

	protected void addTileToDrawNormalProceed(Tile tile) {

		tile.getImageView().flipFront();
		Lists.INSTANCE.drawNormal.getArrayList().addLast(tile);
		Lists.INSTANCE.drawNormal.relocateImageViews();

		Modifiers.INSTANCE.setTileToPlay(tile);

	}

}
