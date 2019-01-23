package ky.sys4u.file.filecopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ky.sys4u.file.exception.CanNotReadWriteFileException;

public class DirectoryCopier {

	private String inputPath;
	private String outputPath;

	public DirectoryCopier(String inputPath,String outputPath) {
	
		if( inputPath== null || outputPath == null )
			throw new IllegalArgumentException();
		
		this.inputPath = inputPath;
		this.outputPath = outputPath;

	}


	public void copy() {
		copy(true);
	}

	public void copy(boolean isCopy) {
		File file = new File(inputPath);
		makeDirectoryRecursively(file, outputPath, isCopy);
	}

	public void move() {
		copy(false);
	}

	private void makeDirectoryRecursively(File file, String outputPath, boolean isCopy) {
		File[] files = file.listFiles();
		for (File nowFile : files) {
			if (nowFile.isFile()) {
				makeCopyFile(nowFile.getAbsolutePath(), outputPath + "/" + nowFile.getName());
			} else {
				File temp = new File(outputPath + "/" + nowFile.getName());
				temp.mkdir();
				makeDirectoryRecursively(nowFile, outputPath + "/" + nowFile.getName(), isCopy);
			}

			if (!isCopy) {
				nowFile.delete();
			}
		}
	}

	private void makeCopyFile(String inputPath, String outputPath) {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputPath));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath))) {
			int data;
			while ((data = bis.read()) != -1) {
				bos.write(data);
			}
		} catch (IOException e) {
			throw new CanNotReadWriteFileException(e);
		}
	}

}
