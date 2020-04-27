package gameState;

import controller.Flow;
import controller.Lists;
import enums.EGameState;

public class EndTurn extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Lists.INSTANCE.ordealCards.getArrayList().isEmpty()) {

			Flow.INSTANCE.getFlow().clear();
			Flow.INSTANCE.getFlow().addFirst(EGameState.END_GAME_WIN);

		}

		Flow.INSTANCE.proceed();

	}

}
