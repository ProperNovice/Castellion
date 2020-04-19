package utils;

public enum MapImageViews {

	INSTANCE;

	private HashMap<ImageViewAble, ImageView> mapImageViews = new HashMap<>();

	public HashMap<ImageViewAble, ImageView> getImageViewsMap() {
		return this.mapImageViews;
	}

}
