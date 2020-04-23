package utils;

public interface ImageViewAble {

	public default ImageView getImageView() {
		return MapImageViews.INSTANCE.getImageViewsMap().getValue(this);
	}

}
