package utils;

public enum Random {

	INSTANCE;

	private java.util.Random random = new java.util.Random();

	public int getRandomNumber(int firstNumber, int secondNumber) {
		return firstNumber + random.nextInt(secondNumber - firstNumber + 1);
	}

	public boolean chanceOutcome(double chance) {

		double rng = this.getRandomNumber(1, 100);

		if (rng <= chance)
			return true;
		else
			return false;

	}

}
