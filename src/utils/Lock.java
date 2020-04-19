package utils;

import com.sun.javafx.tk.Toolkit;

import javafx.application.Platform;

public enum Lock {

	INSTANCE;

	private Object lockObject = new Object();
	private LockType lockType = null;
	private Semaphore semaphore = new Semaphore();

	private Lock() {

	}

	private enum LockType {
		FX, EXECUTOR_SERVICE
	}

	private class Semaphore {

		private java.util.concurrent.Semaphore semaphore = null;

		public Semaphore() {
			this.semaphore = new java.util.concurrent.Semaphore(0);
		}

		public void releasePermit() {
			this.semaphore.release();
		}

		public void acquirePermit() {

			try {
				this.semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		public int availablePermits() {
			return this.semaphore.availablePermits();
		}

	}

	public void lock() {

		Logger.INSTANCE.log("lock");

		if (Platform.isFxApplicationThread()) {

			this.lockType = LockType.FX;
			Toolkit.getToolkit().enterNestedEventLoop(this.lockObject);

		} else if (!Platform.isFxApplicationThread()) {

			this.lockType = LockType.EXECUTOR_SERVICE;
			this.semaphore.acquirePermit();

		}

		Logger.INSTANCE.logNewLine("unlock");

		if (Platform.isFxApplicationThread())
			return;

		Logger.INSTANCE.logNewLine("available permits : " + this.semaphore.availablePermits());

	}

	public void unlock() {

		switch (this.lockType) {

		case FX:
			Toolkit.getToolkit().exitNestedEventLoop(this.lockObject, null);
			break;

		case EXECUTOR_SERVICE:
			this.semaphore.releasePermit();
			break;

		}

	}

}
