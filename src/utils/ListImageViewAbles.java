package utils;

import java.util.Iterator;

import controller.Lists;
import interfaces.ISaveLoadStateAble;
import utils.Animation.AnimationSynch;

public class ListImageViewAbles<T> implements ISaveLoadStateAble, Iterable<T> {

	protected ArrayList<T> arrayList = new ArrayList<>();
	protected ArrayList<T> arrayListRestartGame = new ArrayList<>();
	protected ArrayList<T> arrayListSaveLoadStateGame = new ArrayList<>();
	protected Coordinates coordinates = null;

	public ListImageViewAbles(Coordinates coordinates) {
		this.coordinates = coordinates;
		Lists.INSTANCE.iSaveLoadStateAbles.addLast(this);
		this.coordinates.setList(this.arrayList);
	}

	public ListImageViewAbles(Coordinates coordinates, int capacity) {
		this(coordinates);
		this.arrayList.setCapacity(capacity);
	}

	public void toFront() {

		ImageViewAble imageViewAble = null;

		for (int counter = this.arrayList.size() - 1; counter >= 0; counter--) {

			imageViewAble = (ImageViewAble) this.arrayList.get(counter);
			imageViewAble.getImageView().toFront();

		}

	}

	public void toBack() {

		ImageViewAble imageViewAble = null;

		for (T t : this.arrayList) {

			imageViewAble = (ImageViewAble) t;
			imageViewAble.getImageView().toFront();

		}

	}

	public void animateAsynchronous() {
		executeAction(ImageViewAction.ANIMATE, AnimationSynch.ASYNCHRONOUS);
	}

	public void animateSynchronous() {
		executeAction(ImageViewAction.ANIMATE, AnimationSynch.SYNCHRONOUS);
	}

	public void animateSynchronousLock() {

		animateSynchronous();
		Lock.INSTANCE.lock();

	}

	public void relocateImageViews() {
		executeAction(ImageViewAction.RELOCATE, null);
	}

	public void relocateList(double x, double y) {
		this.coordinates.relocateList(x, y);
	}

	public void relocateList(NumbersPair numbersPair) {
		this.coordinates.relocateList(numbersPair);
	}

	private enum ImageViewAction {
		ANIMATE, RELOCATE
	}

	private void executeAction(ImageViewAction imageViewAction, AnimationSynch animationSynch) {

		ImageView imageView = null;

		ArrayList<T> listReversed = new ArrayList<>(this.arrayList);
		listReversed.reverse();

		for (T t : listReversed) {

			int index = this.arrayList.indexOf(t);
			NumbersPair numbersPair = this.coordinates.getCoordinate(index);

			imageView = ((ImageViewAble) t).getImageView();

			switch (imageViewAction) {

			case ANIMATE:
				Animation.INSTANCE.animate(imageView, numbersPair, animationSynch);
				break;

			case RELOCATE:
				imageView.relocateTopLeft(numbersPair);
				break;

			}

		}

	}

	public ArrayList<T> getArrayList() {
		return this.arrayList;
	}

	@Override
	public void saveStartGame() {
		this.arrayListRestartGame = this.arrayList.clone();
	}

	@Override
	public void loadStartGame() {
		this.arrayList = this.arrayListRestartGame.clone();
	}

	@Override
	public void saveState() {
		this.arrayListSaveLoadStateGame = this.arrayList.clone();
	}

	@Override
	public void loadState() {
		this.arrayList = this.arrayListSaveLoadStateGame.clone();
	}

	@Override
	public Iterator<T> iterator() {
		return this.arrayList.iterator();
	}

}
