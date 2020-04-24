package enums;

import gameState.*;

public enum EGameState {

	START_GAME(new StartGame()),
	END_GAME(new EndGame()),
	RESTART_GAME(new RestartGame()),
	DRAW_PHASE(new DrawPhase()),
	HANDLE_TILE_TO_PLAY(new HandleTileToPlay()),
	HANDLE_TILE_TO_PLAY_DEFENDER(new HandleTileToPlayDefender()),
	HANDLE_TILE_TO_PLAY_TRAITOR(new HandleTileToPlayTraitor()),
	HANDLE_TILE_DISCARDED(new HandleTileDiscarded()),
	RESOLVE_SEER(new ResolveSeer()),
	RESOLVE_JUGGLER(new ResolveJuggler()),
	RESOLVE_CHAMELEON(new ResolveChameleon()),
	RESOLVE_PYRO_DESTROY(new ResolvePyroDestroy()),
	RESOLVE_PYRO_MOVE(new ResolvePyroMove()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
