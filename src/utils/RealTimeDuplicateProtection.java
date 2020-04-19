package utils;

import javafx.animation.AnimationTimer;

public enum RealTimeDuplicateProtection {

	INSTANCE;

	private ArrayList<Object> objectsChecking = new ArrayList<Object>();
	private ArrayList<ArrayList<? extends Object>> objectLists = new ArrayList<ArrayList<? extends Object>>();

	private RealTimeDuplicateProtection() {
		new Timer().start();
	}

	public void addList(ArrayList<? extends Object> list) {
		this.objectLists.addLast(list);
		Logger.INSTANCE.log("list rtdp -> " + this.objectLists.size());
	}

	private void checkLists() {

		this.objectsChecking.clear();

		for (ArrayList<? extends Object> objectList : this.objectLists) {

			for (Object object : objectList.clone())
				if (this.objectsChecking.contains(object))
					ShutDown.INSTANCE.execute("duplicate object found");
				else
					this.objectsChecking.addLast(object);

		}

	}

	private class Timer extends AnimationTimer {

		@Override
		public void handle(long now) {
			checkLists();
		}

	}

}
