package gui;

import controller.Credentials;
import controller.Flow;
import controller.Lists;
import enums.EGameState;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.Animation;
import utils.Executor;
import utils.Logger;
import utils.NumbersPair;
import utils.ShutDown;

public class Castellion extends Application {

	private NumbersPair dimensionsInsets = new NumbersPair(6, 29);
	private double pixesOnTheLeft = 180;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Panel panel = new Panel();

		double width = Credentials.INSTANCE.DimensionsFrame.x + this.dimensionsInsets.x;
		double height = Credentials.INSTANCE.DimensionsFrame.y + this.dimensionsInsets.y;

		Scene scene = new Scene(panel);
		setKeyPressed(scene);

		primaryStage.setScene(scene);
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);

		primaryStage.setTitle(Credentials.INSTANCE.primaryStageTitle);

		primaryStage.setX((Screen.getPrimary().getBounds().getWidth() - width) / 2 - this.pixesOnTheLeft);
		primaryStage.setY((Screen.getPrimary().getBounds().getHeight() - height) / 2);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				ShutDown.INSTANCE.execute();
			}

		});

		primaryStage.show();

		Lists.INSTANCE.instantiate();
		Executor.INSTANCE.runLater(() -> Flow.INSTANCE.executeGameState(EGameState.START_GAME));

	}

	private void setKeyPressed(Scene scene) {

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				KeyCode keyCode = event.getCode();

				Logger.INSTANCE.logNewLine(keyCode + " key code pressed");

				Executor.INSTANCE.runLater(() -> {

					if (keyCode.equals(KeyCode.ESCAPE))
						ShutDown.INSTANCE.execute();

					else if (keyCode.equals(KeyCode.BACK_QUOTE))
						Flow.INSTANCE.executeGameState(EGameState.RESTART_GAME);

					else if (Animation.INSTANCE.isAnimatingSynchronous())
						return;

					Flow.INSTANCE.getCurrentGameState().executeKeyPressed(keyCode);

				});

			}

		});

	}

	public static void main(String[] args) {
		launch(args);
	}

}
