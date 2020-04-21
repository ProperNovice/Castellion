package gameState;

import controller.Flow;
import controller.Modifiers;
import enums.EGameState;
import tile.TileDefender;

public class HandleTileToPlay extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Modifiers.INSTANCE.getTileToPlay() instanceof TileDefender)
			Flow.INSTANCE.executeGameState(EGameState.HANDLE_TILE_TO_PLAY_DEFENDER);
		else
			Flow.INSTANCE.executeGameState(EGameState.HANDLE_TILE_TO_PLAY_TRAITOR);

	}

}
