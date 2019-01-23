package ky.sys4u.file.filetree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileToDirectoryConverter implements Convertable<File, FileTree> {

	private static final String SPACE = "  ";
	private static final String SAVE_PATH = "C:/test/Apartment2";

	@Override
	public FileTree convert(File source) {

		ArrayList<String> dirPathArray = new ArrayList<>();
		int depth = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {

			String str = reader.readLine();
			while ((str = reader.readLine()) != null) {

				depth = str.substring(0, str.indexOf("?��")).length() / SPACE.length();
				dirPathArray.add(depth - 1, str.substring(str.lastIndexOf("?��") + 1));
				if (dirPathArray.get(depth - 1) != null) {
					dirPathArray.add(depth, null);
				}

				String additionalPath = SAVE_PATH;
				for (String path : dirPathArray) {
					if (path == null) {
						break;
					}
					additionalPath += "/" + path;
				}

				File file = new File(additionalPath);
				file.mkdirs();
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}
		return null;
	}
}
