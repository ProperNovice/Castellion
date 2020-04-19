package gui;

import javafx.scene.image.ImageView;
import utils.EventHandler;
import utils.EventHandler.EventHandlerAble;
import utils.Parent;
import utils.ParentInstance;
import utils.ShutDown;

public class Panel extends Parent implements EventHandlerAble {

	private ImageView background = new ImageView("/images/misc/backgroundDark.png");

	public Panel() {

		ParentInstance.INSTANCE.setPanelGameInstance(this);

		this.background.toBack();
		this.background.setOnMousePressed(new EventHandler(this));

		this.getChildren().add(this.background);
		this.getChildren().add(new Parent());

	}

	@Override
	public void handleMouseButtonPressedSecondary() {
		ShutDown.INSTANCE.execute();
	}

}
