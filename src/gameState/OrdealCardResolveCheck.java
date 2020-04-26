package gameState;

import controller.Board;
import controller.Flow;
import controller.OrdealCardTraitorList;
import enums.EGameState;

public class OrdealCardResolveCheck extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (OrdealCardTraitorList.INSTANCE.list.getArrayList().isMaxedCapacity()
				|| Board.INSTANCE.listBoard.isMaxedCapacity())
			Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_ORDEAL_CARD);

		Flow.INSTANCE.proceed();

	}

}
