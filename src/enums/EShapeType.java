package enums;

public enum EShapeType {

	CIRCLE("Circle"), SQUARE("Square"), TRIANGLE("Triangle");

	private String fileName = null;

	private EShapeType(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

}
