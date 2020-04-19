package enums;

import gameState.*;

public enum EGameState {

	START_GAME(new StartGame()),
	END_GAME(new EndGame()),
	RESTART_GAME(new RestartGame()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
