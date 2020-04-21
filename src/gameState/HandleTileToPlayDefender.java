package gameState;

import controller.Lists;
import controller.Modifiers;
import enums.EShapeType;
import tile.Tile;
import tile.TileDefender;

public class HandleTileToPlayDefender extends AGameState {

	@Override
	public void handleGameStateChange() {

		Tile tileToPlay = Modifiers.INSTANCE.getTileToPlay();
		TileDefender tileDefender = (TileDefender) tileToPlay;
		EShapeType eShapeType = tileDefender.getShapeType();

		Lists.INSTANCE.board.handleEmptyTilesForPlacingDefenderTile(eShapeType);

	}

}
