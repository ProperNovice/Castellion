package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EDefenderFaction;
import enums.EGameState;
import enums.EText;
import interfaces.IDefenderFactionAble;
import interfaces.IShapeTypeAble;
import tile.Tile;
import tile.TileDefender;
import utils.Logger;

public class HandleTileDiscarded extends AGameState {

	private EDefenderFaction eDefenderFaction = null;

	@Override
	public void handleGameStateChange() {

		if (abilityTileCanBeUsed())
			EText.USE_ABILITY.showText();

		EText.CONTINUE_WITHOUT_RESOLVING.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		if (eText.equals(EText.CONTINUE_WITHOUT_RESOLVING)) {
			Flow.INSTANCE.proceed();
			return;
		}

		EGameState eGameState = null;

		switch (this.eDefenderFaction) {

		case CHAMELEON:
			eGameState = EGameState.RESOLVE_CHAMELEON;
			break;

		case JUGGLER:
			eGameState = EGameState.RESOLVE_JUGGLER;
			break;

		case PYRO:
			eGameState = EGameState.RESOLVE_PYRO_DESTROY;
			break;

		case SEER:
			eGameState = EGameState.RESOLVE_SEER;
			break;

		}

		Flow.INSTANCE.executeGameState(eGameState);

	}

	private boolean abilityTileCanBeUsed() {

		Tile tile = Modifiers.INSTANCE.getTileToPlay();

		IDefenderFactionAble iDefenderFactionAble = (IDefenderFactionAble) Modifiers.INSTANCE.getTileToPlay();
		this.eDefenderFaction = iDefenderFactionAble.getDefenderFaction();

		boolean canBeResolved = true;

		switch (this.eDefenderFaction) {

		case CHAMELEON:
			canBeResolved = chameleonCanBeResolved(tile);
			break;

		case JUGGLER:
			canBeResolved = jugglerCanBeResolved();
			break;

		case PYRO:
			canBeResolved = pyroCanBeResolved();
			break;

		case SEER:
			canBeResolved = seerCanBeResolved();
			break;

		}

		Logger.INSTANCE.log(eDefenderFaction + " can be resolved");
		Logger.INSTANCE.logNewLine(canBeResolved);

		return canBeResolved;

	}

	private boolean seerCanBeResolved() {

		if (!Lists.INSTANCE.deckNormal.getArrayList().isEmpty())
			return true;

		if (!Lists.INSTANCE.deckSafe.getArrayList().isEmpty())
			return true;

		return false;

	}

	private boolean chameleonCanBeResolved(Tile tile) {

		IShapeTypeAble iShapeTypeAble = (IShapeTypeAble) tile;
		IDefenderFactionAble iDefenderFactionAble = (IDefenderFactionAble) tile;

		for (Tile tileTemp : Lists.INSTANCE.discardPile) {

			if (!(tileTemp instanceof TileDefender))
				continue;

			TileDefender tileDefenderTemp = (TileDefender) tileTemp;

			if (!iShapeTypeAble.getShapeType().equals(tileDefenderTemp.getShapeType()))
				continue;

			if (iDefenderFactionAble.getDefenderFaction().equals(tileDefenderTemp.getDefenderFaction()))
				continue;

			return true;

		}

		return false;
	}

	private boolean jugglerCanBeResolved() {
		return Lists.INSTANCE.board.foundationSize() > 1;
	}

	private boolean pyroCanBeResolved() {
		return !Lists.INSTANCE.board.listBoard.isEmpty();
	}

}
