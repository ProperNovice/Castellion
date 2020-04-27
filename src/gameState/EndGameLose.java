package gameState;

import controller.Flow;
import enums.EGameState;
import enums.EText;

public class EndGameLose extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.YOU_LOST.showText();
		EText.RESTART.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.executeGameState(EGameState.RESTART_GAME);
	}

}
