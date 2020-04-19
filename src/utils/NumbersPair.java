package utils;

public class NumbersPair {

	public double x;
	public double y;

	public NumbersPair(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public void print() {

		Logger.INSTANCE.log("x -> " + this.x);
		Logger.INSTANCE.log("y -> " + this.y);
		Logger.INSTANCE.newLine();

	}

}
