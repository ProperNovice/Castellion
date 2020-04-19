package utils;

public enum ShutDown {

	INSTANCE;

	public void execute() {

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine("System.exit(0)");
		System.exit(0);

	}

	public void execute(String log) {

		Logger.INSTANCE.logNewLine(log);
		execute();

	}

}
