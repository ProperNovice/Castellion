package utils;

import controller.Credentials;

public enum SelectImageViewManager {

	INSTANCE;

	private HashMap<ImageViewAble, SelectImageView> hashMap = new HashMap<ImageViewAble, SelectImageView>();

	private SelectImageViewManager() {

	}

	public void addSelectImageView(ImageViewAble imageViewAble) {

		if (this.hashMap.containsKey(imageViewAble))
			ShutDown.INSTANCE.execute(this);

		SelectImageView selectImageView = ObjectPool.INSTANCE.acquire(SelectImageView.class);
		this.hashMap.put(imageViewAble, selectImageView);

		selectImageView.getImageView().setVisible(true);

		ImageView imageView = imageViewAble.getImageView();
		double width = imageView.getWidth() * Credentials.INSTANCE.SELECT_IMAGEVIEW_WIDTH;
		selectImageView.getImageView().setWidth(width);
		selectImageView.getImageView().relocateCenter(imageView.getCenter());

	}

	public void releaseSelectImageView(ImageViewAble imageViewAble) {

		if (!this.hashMap.containsKey(imageViewAble))
			ShutDown.INSTANCE.execute(this);

		SelectImageView selectImageView = this.hashMap.getValue(imageViewAble);
		selectImageView.getImageView().setVisible(false);
		ObjectPool.INSTANCE.release(selectImageView);

		this.hashMap.remove(imageViewAble);

	}

	public void releaseSelectImageViews() {

		for (ImageViewAble imageViewAble : this.hashMap.clone())
			releaseSelectImageView(imageViewAble);

	}

	public boolean isSelectedImageView(ImageViewAble imageViewAble) {
		return this.hashMap.containsKey(imageViewAble);
	}

	public void handleMouseButtonPressedPrimary(SelectImageView selectImageView) {

		ImageViewAble imageViewAble = this.hashMap.getKey(selectImageView);
		ImageView imageView = imageViewAble.getImageView();
		imageView.geteEventHandlerAble().handleMouseButtonPressedPrimary();

	}

}
