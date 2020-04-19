package utils;

public enum ParentInstance {

	INSTANCE;

	private Parent parentInstance = null;

	public void setPanelGameInstance(Parent parent) {
		this.parentInstance = parent;
	}

	public Parent getParentInstance() {
		return this.parentInstance;
	}

}
