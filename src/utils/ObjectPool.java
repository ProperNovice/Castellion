package utils;

import java.lang.reflect.InvocationTargetException;

public enum ObjectPool {

	INSTANCE;

	private HashMap<Class<?>, ArrayList<Object>> objectPool = new HashMap<>();

	public void release(Object object) {
		this.objectPool.get(object.getClass()).addLast(object);
	}

	@SuppressWarnings("unchecked")
	public <T> T acquire(Class<T> objectClass) {

		if (!this.objectPool.containsKey(objectClass))
			this.objectPool.put(objectClass, new ArrayList<Object>());

		T t = null;

		if (this.objectPool.get(objectClass).isEmpty())
			t = createNewObject(objectClass);
		else
			t = (T) this.objectPool.get(objectClass).removeFirst();

		return t;

	}

	private <T> T createNewObject(Class<T> objectClass) {

		try {

			return (T) objectClass.getConstructor().newInstance();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return null;

	}

}
