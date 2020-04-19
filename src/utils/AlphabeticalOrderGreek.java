package utils;

import java.util.ArrayList;

public enum AlphabeticalOrderGreek {

	INSTANCE;

	private ArrayList<String> alphabeticalOrder = new ArrayList<>();

	private AlphabeticalOrderGreek() {

	}

	public String firstInAlphabeticalOrder(String first, String second) {

		if (this.alphabeticalOrder.isEmpty())
			createList();

		String firstString = first.toLowerCase();
		String secondString = second.toLowerCase();

		firstString = replaceTones(firstString);
		secondString = replaceTones(secondString);

		return getFirstInAlphabeticalOrder(first, second);

	}

	private void createList() {

		this.alphabeticalOrder.add(" ");
		this.alphabeticalOrder.add(".");
		this.alphabeticalOrder.add("&");
		this.alphabeticalOrder.add("á");
		this.alphabeticalOrder.add("â");
		this.alphabeticalOrder.add("ã");
		this.alphabeticalOrder.add("ä");
		this.alphabeticalOrder.add("å");
		this.alphabeticalOrder.add("æ");
		this.alphabeticalOrder.add("ç");
		this.alphabeticalOrder.add("è");
		this.alphabeticalOrder.add("é");
		this.alphabeticalOrder.add("ê");
		this.alphabeticalOrder.add("ë");
		this.alphabeticalOrder.add("ì");
		this.alphabeticalOrder.add("í");
		this.alphabeticalOrder.add("î");
		this.alphabeticalOrder.add("ï");
		this.alphabeticalOrder.add("ð");
		this.alphabeticalOrder.add("ñ");
		this.alphabeticalOrder.add("ó");
		this.alphabeticalOrder.add("ô");
		this.alphabeticalOrder.add("õ");
		this.alphabeticalOrder.add("ö");
		this.alphabeticalOrder.add("÷");
		this.alphabeticalOrder.add("ø");
		this.alphabeticalOrder.add("ù");

	}

	private String replaceTones(String string) {

		string = string.replaceAll("Ü", "á");
		string = string.replaceAll("Ý", "å");
		string = string.replaceAll("Þ", "ç");
		string = string.replaceAll("ß", "é");
		string = string.replaceAll("ü", "ï");
		string = string.replaceAll("ý", "õ");
		string = string.replaceAll("þ", "ù");

		return string;
	}

	private String getFirstInAlphabeticalOrder(String first, String second) {

		String stringToReturn = null;

		for (int counter = 0; counter < Math.min(first.length(), second.length()); counter++) {

			if (this.alphabeticalOrder.indexOf(getStringAtIndex(first, counter)) < this.alphabeticalOrder
					.indexOf(getStringAtIndex(second, counter))) {
				stringToReturn = first;
				break;

			} else if (this.alphabeticalOrder.indexOf(getStringAtIndex(second, counter)) < this.alphabeticalOrder
					.indexOf(getStringAtIndex(first, counter))) {
				stringToReturn = second;
				break;
			}

		}

		if (stringToReturn != null)
			return stringToReturn;

		if (first.length() < second.length())
			return first;

		return second;

	}

	private static String getStringAtIndex(String string, int index) {
		return string.substring(index, index + 1);
	}

}
