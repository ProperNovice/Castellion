package gameState;

import controller.Flow;
import controller.Lists;
import enums.EText;
import tile.Tile;
import utils.ArrayList;
import utils.DirectionEnum;
import utils.ImageViewAble;
import utils.LineCast;
import utils.NumbersPair;
import utils.SelectImageViewManager;
import utils.Text;

public class ResolveJuggler extends AGameState implements ImageViewAble {

	private ArrayList<Tile> selected = new ArrayList<Tile>();

	@Override
	public void handleGameStateChange() {

		this.selected.clear();
		Lists.INSTANCE.board.setTilesCanResolvedbyJuggler();

		EText.SELECT_FIRST_TILE.showText();

	}

	@Override
	protected void handleTileBoardPressedNonEmpty(Tile tile) {

		if (!SelectImageViewManager.INSTANCE.hasSelectImageView(tile))
			return;

		Text.INSTANCE.concealText();

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		this.selected.addLast(tile);

		if (this.selected.size() == 1)
			handleOneTileSelected();
		else
			handleTwoTilesSelected();

	}

	private void handleOneTileSelected() {

		ArrayList<Tile> lineCast = new ArrayList<Tile>();
		lineCast.addAll(LineCast.INSTANCE.lineCast(this.selected.getFirst(), DirectionEnum.LEFT, 1));
		lineCast.addAll(LineCast.INSTANCE.lineCast(this.selected.getFirst(), DirectionEnum.RIGHT, 1));

		for (Tile tile : lineCast)
			SelectImageViewManager.INSTANCE.addSelectImageView(tile);

		if (lineCast.size() == 1) {

			this.selected.addLast(lineCast.getFirst());
			handleTileBoardPressedNonEmpty(lineCast.getFirst());

		} else
			EText.SELECT_SECOND_TILE.showText();

	}

	private void handleTwoTilesSelected() {

		NumbersPair centerFirst = this.selected.getFirst().getImageView().getCenter();
		NumbersPair centerLast = this.selected.getLast().getImageView().getCenter();

		this.selected.getFirst().getImageView().relocateCenter(centerLast);
		this.selected.getLast().getImageView().relocateCenter(centerFirst);
		
		Flow.INSTANCE.proceed();

	}

}
