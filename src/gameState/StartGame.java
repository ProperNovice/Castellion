package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EDefenderFaction;
import enums.EGameState;
import enums.EShapeType;
import tile.Tile;
import tile.TileDefender;
import tile.TileEmpty;
import utils.ListImageViewAbles;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		Flow.INSTANCE.getFlow().addFirst(EGameState.END_GAME);

//		addTiletoListSeer(new TileDefender(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE));
//		addTiletoListSeer(new TileTraitorNormal());
//		addTiletoListSeer(new TileDefender(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE));
//		addTiletoListSeer(new TileTraitorBlack());

//		addTileToDrawNormalProceed(EDefenderFaction.CHAMELEON, EShapeType.CIRCLE);

		addTileToDiscardPile(EDefenderFaction.JUGGLER, EShapeType.CIRCLE);

		leaveTilesToList(Lists.INSTANCE.deckNormal, 2);
		leaveTilesToList(Lists.INSTANCE.deckSafe, 1);

		addTileToBoard(EDefenderFaction.SEER, EShapeType.SQUARE, 3);
		addTileToBoard(EDefenderFaction.PYRO, EShapeType.SQUARE, 3);
		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.CIRCLE, 2);
		addTileToBoard(EDefenderFaction.PYRO, EShapeType.CIRCLE, 0);
		addTileToBoard(EDefenderFaction.SEER, EShapeType.CIRCLE, 1);
		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE, 1);
		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE, 4);
		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.SQUARE, 4);
		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE, 4);
//		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.SQUARE, 5);

//		Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_SEER);
		Flow.INSTANCE.getFlow().addFirst(EGameState.HANDLE_TILE_DISCARDED);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.HANDLE_TILE_TO_PLAY);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.DRAW_PHASE);

		Flow.INSTANCE.proceed();

		TileEmpty tileEmpty = new TileEmpty();
		tileEmpty.getImageView().relocateTopLeft(100, 100);

	}

	protected void addTiletoListSeer(Tile tile) {

		tile.getImageView().flipFront();
		Lists.INSTANCE.drawSeer.getArrayList().addLast(tile);
		Lists.INSTANCE.drawSeer.relocateImageViews();

	}

	protected void addTileToDrawNormalProceed(EDefenderFaction eDefenderFaction, EShapeType eShapeType) {

		Tile tile = new TileDefender(eDefenderFaction, eShapeType);
		tile.getImageView().flipFront();

		Lists.INSTANCE.drawNormal.getArrayList().addLast(tile);
		Lists.INSTANCE.drawNormal.relocateImageViews();

		Modifiers.INSTANCE.setTileToPlay(tile);

	}

	protected void addTileToBoard(EDefenderFaction eDefenderFaction, EShapeType eShapeType, int column) {

		Tile tile = new TileDefender(eDefenderFaction, eShapeType);
		tile.getImageView().flipFront();

		Lists.INSTANCE.board.jUnitAddTileToBoardRelocate(tile, column);

	}

	protected void addTileToDiscardPile(EDefenderFaction eDefenderFaction, EShapeType eShapeType) {

		Tile tile = new TileDefender(eDefenderFaction, eShapeType);
		tile.getImageView().flipFront();

		Lists.INSTANCE.discardPile.getArrayList().addLast(tile);
		Lists.INSTANCE.discardPile.relocateImageViews();

		Modifiers.INSTANCE.setTileToPlay(tile);

	}

	protected void leaveTilesToList(ListImageViewAbles<Tile> list, int tilesToLeave) {

		while (list.getArrayList().size() > tilesToLeave) {

			Tile tile = list.getArrayList().removeFirst();
			tile.getImageView().setVisible(false);

		}

	}

}
