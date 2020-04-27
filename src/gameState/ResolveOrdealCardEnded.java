package gameState;

import controller.Flow;
import controller.Lists;
import enums.EGameState;

public class ResolveOrdealCardEnded extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Lists.INSTANCE.board.listBoard.isMaxedCapacity())
			Flow.INSTANCE.getFlow().addFirst(EGameState.ORDEAL_CARD_RESOLVE_CHECK);

		Flow.INSTANCE.proceed();

	}

}
