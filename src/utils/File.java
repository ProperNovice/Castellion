package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public enum File {

	INSTANCE;

	private java.io.File file = new java.io.File("txt.txt");

	private File() {

	}

	public void writeToFile(ArrayList<String> list, String fileName) {

		this.file = new java.io.File(fileName + ".txt");

		if (this.file.exists())
			this.file.delete();

		createNewFile();

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file))) {

			while (!list.isEmpty()) {

				bufferedWriter.write(list.removeFirst());
				bufferedWriter.newLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> readFromFile(String fileName) {

		this.file = new java.io.File(fileName + ".txt");

		ArrayList<String> fileList = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {

			String line = null;

			while (true) {

				line = bufferedReader.readLine();

				if (line == null)
					break;

				fileList.addLast(line);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileList;

	}

	private void createNewFile() {
		try {
			this.file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
