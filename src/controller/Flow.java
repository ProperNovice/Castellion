package controller;

import enums.EGameState;
import gameState.AGameState;
import utils.ArrayList;
import utils.Logger;

public enum Flow {

	INSTANCE;

	private EGameState currentGameState = null;
	private ArrayList<EGameState> flow = new ArrayList<>();
	private ArrayList<EGameState> flowTurn = new ArrayList<>();

	private Flow() {
		createTurns();
	}

	public void proceed() {

		if (this.flow.isEmpty())
			this.flow.addAll(this.flowTurn);

		this.currentGameState = this.flow.removeFirst();
		executeGameState();

	}

	public void executeGameState(EGameState eGameState) {

		this.currentGameState = eGameState;
		executeGameState();

	}

	private void createTurns() {

		this.flowTurn.addLast(EGameState.DRAW_PHASE);
		this.flowTurn.addLast(EGameState.HANDLE_TILE_TO_PLAY);
		this.flowTurn.addLast(EGameState.ORDEAL_CARD_RESOLVE_CHECK);
		this.flowTurn.addLast(EGameState.RESOLVE_ORDEAL_CARD_ENDED);
		this.flowTurn.addLast(EGameState.END_TURN);

	}

	private void executeGameState() {

		DefensiveFormationManager.INSTANCE.updateTexts();

		Logger.INSTANCE.log("executing gamestate");
		Logger.INSTANCE.logNewLine(this.currentGameState);

		this.currentGameState.getGameState().handleGameStateChange();

	}

	public AGameState getCurrentGameState() {
		return this.currentGameState.getGameState();
	}

	public ArrayList<EGameState> getFlow() {
		return this.flow;
	}

}
