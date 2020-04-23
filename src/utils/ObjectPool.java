package utils;

import java.lang.reflect.InvocationTargetException;

public enum ObjectPool {

	INSTANCE;

	private HashMap<Class<?>, ArrayList<Object>> objectPool = new HashMap<>();
	private HashMap<Class<?>, Integer> objectPoolCounter = new HashMap<>();

	public void release(Object object) {
		this.objectPool.getValue(object.getClass()).addLast(object);
	}

	@SuppressWarnings("unchecked")
	public <T> T acquire(Class<T> objectClass) {

		if (!this.objectPool.containsKey(objectClass))
			this.objectPool.put(objectClass, new ArrayList<Object>());

		T t = null;

		if (this.objectPool.getValue(objectClass).isEmpty())
			t = createNewObject(objectClass);
		else
			t = (T) this.objectPool.getValue(objectClass).removeFirst();

		return t;

	}

	private <T> T createNewObject(Class<T> objectClass) {

		try {

			int objectsCreated = 0;

			if (this.objectPoolCounter.containsKey(objectClass))
				objectsCreated = this.objectPoolCounter.getValue(objectClass);

			objectsCreated++;
			this.objectPoolCounter.put(objectClass, objectsCreated);

			return (T) objectClass.getConstructor().newInstance();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		ShutDown.INSTANCE.execute(this);
		return null;

	}

	public void print() {

		String stars = "************";

		Logger.INSTANCE.log(stars);
		Logger.INSTANCE.log("object pool");

		for (Class<?> classObject : this.objectPool) {

			Logger.INSTANCE.newLine();
			Logger.INSTANCE.log(classObject);
			Logger.INSTANCE.log(this.objectPoolCounter.getValue(classObject));

		}

		Logger.INSTANCE.logNewLine(stars);

	}

}
