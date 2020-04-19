package utils;

public enum Permutations {

	INSTANCE;

	private ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
	private int total, permutations;

	private Permutations() {

	}

	public ArrayList<ArrayList<Integer>> getPermutations(int total, int permutations) {

		this.results.clear();

		this.total = total;
		this.permutations = permutations;

		if (this.total < this.permutations)
			return results;

		addLeaves(new ArrayList<Integer>(), 0);

		return results;

	}

	private void trimList() {

		if (permutationsComplete())
			return;

		ArrayList<Integer> list = this.results.remove(0);

		int lastNumberAdded = list.get(list.size() - 1);
		int numberToAdd = lastNumberAdded + 1;

		addLeaves(list, numberToAdd);

	}

	private void addLeaves(ArrayList<Integer> list, int number) {

		if (number == this.total) {
			trimList();
			return;
		}

		ArrayList<Integer> listClone = new ArrayList<>(list);

		listClone.addLast(number);
		this.results.addLast(listClone);

		number++;

		addLeaves(list, number);

	}

	private boolean permutationsComplete() {
		return this.results.get(0).size() == this.permutations;
	}

}
