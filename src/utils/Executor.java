package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum Executor {

	INSTANCE;

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private ThreadRunning threadRunning = ThreadRunning.FX;

	private Executor() {

	}

	public void sleep(final long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void runLater(Runnable runnable) {

		switch (this.threadRunning) {

		case EXECUTOR_SERVICE:
			runLaterExecutorService(runnable);
			break;

		case FX:
			runLaterFX(runnable);
			break;

		}

	}

	private void runLaterExecutorService(Runnable runnable) {
		this.executorService.submit(runnable);
	}

	private void runLaterFX(Runnable runnable) {
		runnable.run();
	}

	private enum ThreadRunning {
		EXECUTOR_SERVICE, FX
	}

	public void sleepExecuteRunnableFX(long duration, Runnable runnable) {

		runLaterExecutorService(() -> {

			sleep(duration);
			runLaterFX(runnable);

		});

	}
}
