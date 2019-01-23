package ky.sys4u.file.filetree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ky.sys4u.file.exception.ConversionException;


public class FileTreeToFileConverter implements Convertable<FileTree, File> {

	private static final String SAVE_PATH = "C:/test/FileTreeToFileConvertResult/test.txt";

	@Override
	public File convert(FileTree source) {

		File file = new File(SAVE_PATH);

		try (FileWriter writer = new FileWriter(file, false)) {
			writer.write(source.toString());
			writer.flush();
		} catch (IOException e) {
			throw new ConversionException(e);
		}

		return file;
	}

}
