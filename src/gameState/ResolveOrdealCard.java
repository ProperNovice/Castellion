package gameState;

import cards.CardOrdeal;
import controller.Board;
import controller.DefensiveFormationManager;
import controller.Flow;
import controller.Lists;
import controller.OrdealCardTraitorList;
import enums.EGameState;
import enums.EOrdealAbility;
import enums.EText;
import tile.Tile;

public class ResolveOrdealCard extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.RESOLVE_ORDEAL_CARD.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		clearTraitorTiles();

		if (Board.INSTANCE.foundationSize() < 6) {

			Flow.INSTANCE.getFlow().clear();
			Flow.INSTANCE.executeGameState(EGameState.END_GAME_LOSE);
			return;

		}

	}

	private void clearTraitorTiles() {

		Lists.INSTANCE.ordealCards.getArrayList().getFirst().getImageView().flipFront();

		for (Tile tile : OrdealCardTraitorList.INSTANCE.list)
			tile.getImageView().setVisible(false);

		OrdealCardTraitorList.INSTANCE.list.getArrayList().clear();

	}

	private boolean ordealCardRequirementsAreMet() {

		CardOrdeal ordealCard = Lists.INSTANCE.ordealCards.getArrayList().getFirst();
		EOrdealAbility eOrdealAbility = ordealCard.getEOrdealAbility();
		int abilityInt = ordealCard.getOrdealAbilityInt();

		switch (eOrdealAbility) {

		case BASTION:
			return (DefensiveFormationManager.INSTANCE.bastions >= abilityInt);

		case FOUNDATION:
			
			for (int counter = 1; counter <= abilityInt; counter++) {
				
			}
			
			break;

		case LINE_OF_DEFENCE:
			return (DefensiveFormationManager.INSTANCE.lineOfDefences >= abilityInt);

		case TOWER:
			return (DefensiveFormationManager.INSTANCE.towers >= abilityInt);

		}

		return false;

	}

}
