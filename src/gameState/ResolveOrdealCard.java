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
import utils.Logger;

public class ResolveOrdealCard extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.RESOLVE_ORDEAL_CARD.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		clearTraitorTiles();

		CardOrdeal ordealCard = Lists.INSTANCE.ordealCards.getArrayList().getFirst();
		EOrdealAbility eOrdealAbility = ordealCard.getEOrdealAbility();
		int abilityInt = ordealCard.getOrdealAbilityInt();

		if (Board.INSTANCE.foundationSize() < 6) {
			EText.NOT_COMPLETE_FOUNDATION.showText();
			loseGame();
			return;

		} else if (ordealCardRequirementsAreMet(eOrdealAbility, abilityInt))
			resolveOrdealRequirementsAreMet();
		else
			resolveOrdealRequirementsAreNotMet();

	}

	private void clearTraitorTiles() {

		Lists.INSTANCE.ordealCards.getArrayList().getFirst().getImageView().flipFront();

		for (Tile tile : OrdealCardTraitorList.INSTANCE.list)
			tile.getImageView().setVisible(false);

		OrdealCardTraitorList.INSTANCE.list.getArrayList().clear();

	}

	private boolean ordealCardRequirementsAreMet(EOrdealAbility eOrdealAbility, int abilityInt) {

		boolean requirementsAreMet = true;

		switch (eOrdealAbility) {

		case BASTION:
			requirementsAreMet = (DefensiveFormationManager.INSTANCE.bastions >= abilityInt);
			break;

		case FOUNDATION:

			for (int counter = 1; counter <= abilityInt; counter++) {

				Lists.INSTANCE.board.destroyFoundationAndShiftBoard();

				if (Board.INSTANCE.foundationSize() < 6) {
					requirementsAreMet = false;
					break;
				}

			}

			break;

		case LINE_OF_DEFENCE:
			requirementsAreMet = (DefensiveFormationManager.INSTANCE.lineOfDefences >= abilityInt);
			break;

		case TOWER:
			requirementsAreMet = (DefensiveFormationManager.INSTANCE.towers >= abilityInt);
			break;

		}

		Logger.INSTANCE.log("Resolving");
		Logger.INSTANCE.log(eOrdealAbility);
		Logger.INSTANCE.log(abilityInt);
		Logger.INSTANCE.logNewLine(requirementsAreMet);

		return requirementsAreMet;

	}

	private void loseGame() {

		Flow.INSTANCE.getFlow().clear();
		Flow.INSTANCE.executeGameState(EGameState.END_GAME_LOSE);

	}

	private void resolveOrdealRequirementsAreMet() {

		CardOrdeal cardOrdeal = Lists.INSTANCE.ordealCards.getArrayList().removeFirst();
		cardOrdeal.getImageView().setVisible(false);
		Flow.INSTANCE.proceed();

	}

	private void resolveOrdealRequirementsAreNotMet() {
		Flow.INSTANCE.executeGameState(EGameState.DESTROY_TILES_DUE_TO_ORDEAL_CARD);
	}

}
