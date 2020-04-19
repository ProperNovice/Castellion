package utils;

public class Image {

	private javafx.scene.image.Image image = null;

	public Image(String path) {

		String finalPath = "/images/" + path;
		this.image = new javafx.scene.image.Image(finalPath);

	}

	public javafx.scene.image.Image getImage() {
		return this.image;
	}

}
