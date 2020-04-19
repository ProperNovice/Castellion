package enums;

public enum EDefenderFaction {

	CHAMELEON("Chameleon"), JUGGLER("Juggler"), PYRO("Pyro"), SEER("Seer");

	private String fileName = null;

	private EDefenderFaction(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

}
