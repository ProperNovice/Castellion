package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serialization {

	private static String textFile = "txt.txt";
	private static Path path = Paths.get(textFile);

	private Serialization() {

	}

	public static void writeToFile(Object object) {

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(textFile))) {

			objectOutputStream.writeObject(object);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Object readFromFile() {

		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(textFile))) {

			Object object = (Object) objectInputStream.readObject();

			return object;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static boolean fileExists() {
		return Files.exists(path,
				new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
	}

}
