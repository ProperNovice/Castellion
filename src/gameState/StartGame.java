package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import controller.OrdealCardTraitorList;
import enums.EDefenderFaction;
import enums.EGameState;
import enums.EShapeType;
import tile.Tile;
import tile.TileDefender;
import tile.TileTraitorNormal;
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
		addTileToDiscardPile(EDefenderFaction.PYRO, EShapeType.SQUARE);
		addTileToDiscardPile(EDefenderFaction.SEER, EShapeType.CIRCLE);
		addTileToDiscardPile(EDefenderFaction.SEER, EShapeType.CIRCLE);
		addTileToDiscardPile(EDefenderFaction.CHAMELEON, EShapeType.CIRCLE);
		addTileToDiscardPile(EDefenderFaction.PYRO, EShapeType.SQUARE);

//		leaveTilesToList(Lists.INSTANCE.deckNormal, 2);
//		leaveTilesToList(Lists.INSTANCE.deckSafe, 1);

		addTileToBoard(EDefenderFaction.PYRO, EShapeType.CIRCLE, 0);
		addTileToBoard(EDefenderFaction.PYRO, EShapeType.CIRCLE, 0);
		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.CIRCLE, 0);
		addTileToBoard(EDefenderFaction.PYRO, EShapeType.CIRCLE, 1);
		addTileToBoard(EDefenderFaction.PYRO, EShapeType.TRIANGLE, 1);
		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE, 1);
		addTileToBoard(EDefenderFaction.SEER, EShapeType.CIRCLE, 2);
//		addTileToBoard(EDefenderFaction.SEER, EShapeType.CIRCLE, 2);
//		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.CIRCLE, 2);
//		addTileToBoard(EDefenderFaction.SEER, EShapeType.CIRCLE, 2);
//		addTileToBoard(EDefenderFaction.SEER, EShapeType.CIRCLE, 2);
		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.SQUARE, 3);
//		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE, 3);
//		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE, 3);
//		addTileToBoard(EDefenderFaction.SEER, EShapeType.TRIANGLE, 3);
//		addTileToBoard(EDefenderFaction.SEER, EShapeType.TRIANGLE, 3);
		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE, 4);
		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.SQUARE, 4);
		addTileToBoard(EDefenderFaction.SEER, EShapeType.TRIANGLE, 4);
		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE, 4);
//		addTileToBoard(EDefenderFaction.CHAMELEON, EShapeType.TRIANGLE, 4);
//		addTileToBoard(EDefenderFaction.JUGGLER, EShapeType.TRIANGLE, 4);
		addTileToBoard(EDefenderFaction.PYRO, EShapeType.SQUARE, 5);
//		addTileToBoard(EDefenderFaction.PYRO, EShapeType.SQUARE, 5);
//		addTileToBoard(EDefenderFaction.PYRO, EShapeType.SQUARE, 5);
//		addTileToBoard(EDefenderFaction.PYRO, EShapeType.SQUARE, 5);
		Lists.INSTANCE.board.relocateBoard();

		Flow.INSTANCE.getFlow().addFirst(EGameState.DESTROY_TILES_DUE_TO_ORDEAL_CARD);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.ORDEAL_CARD_RESOLVE_CHECK);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_PYRO_MOVE);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_PYRO_DESTROY);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_CHAMELEON);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_JUGGLER);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.RESOLVE_SEER);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.HANDLE_TILE_DISCARDED);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.HANDLE_TILE_TO_PLAY);
//		Flow.INSTANCE.getFlow().addFirst(EGameState.DRAW_PHASE);

		addTraitorTileToOrdealCard();
		addTraitorTileToOrdealCard();

		Flow.INSTANCE.proceed();

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

	protected void addTraitorTileToOrdealCard() {
		Tile tile = new TileTraitorNormal();
		tile.getImageView().flipFront();
		OrdealCardTraitorList.INSTANCE.addTraitorTileRelocate(tile);
	}

}
