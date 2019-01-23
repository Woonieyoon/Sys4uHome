package ky.sys4u.file.filecopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ky.sys4u.file.exception.CanNotReadWriteFileException;

public class FileDirCopier {

	private File inputFile;
	private File outputFile;

	public FileDirCopier(String inputPath, String outputPath) {

		if (inputPath == null || outputPath == null)
			throw new IllegalArgumentException();

		checkPath(inputPath, outputPath);
	}

	public void checkPath(String inputPath, String outputPath) {

		inputFile = new File(inputPath);
		outputFile = new File(outputPath);	

		if (inputFile.isFile()) {
			if (outputFile.isDirectory()) {
				outputFile = new File(outputPath + File.separator + inputFile.getName());

				inputPath = inputPath.substring(inputPath.lastIndexOf(".") + 1);
				outputPath = inputPath.substring(outputPath.lastIndexOf(".") + 1);

				if (!inputPath.equals(outputPath)) {
					new IllegalArgumentException("파일 확장자가 다릅니다");
				}
			}
			
			inputPath = inputPath.substring(inputPath.lastIndexOf(".")+1);
			outputPath = inputPath.substring(outputPath.lastIndexOf(".") + 1);
			
		} else {
			if (outputFile.isFile()) {
				new IllegalArgumentException("디렉토리를 파일로 복사할수 없습니다");
			}
		}
	}

	// 메소드에 boolean 있다는것은 해야할 행동이 2가지는 된다는것이다.

	public void copy() {
		copy(true);
	}

	public void copy(boolean isCopy) {
		makeDirectoryRecursively(inputFile, outputFile, isCopy);
	}


	public void move() {
		copy(false);
	}

	private void makeDirectoryRecursively(File inputFile, File outputFile, boolean isCopy) {

		for (File nowFile : inputFile.listFiles()) {

				if (nowFile.isFile()) {
					makeCopyFile(nowFile.getAbsolutePath(), makeFilePath(outputFile.getAbsolutePath(), nowFile));
				} else {
					outputFile = new File(makeFilePath(outputFile.getAbsolutePath(), nowFile));
					outputFile.mkdir();
					makeDirectoryRecursively(nowFile, outputFile, isCopy);
				}
				deleteCheck(nowFile, isCopy);
		}
	}

	// 확장자가 없는 파일이 있다.
	// 똑같은 이름으로 할래 다른이름으로 할래? 확장자가 필요
	public void deleteCheck(File inputFile, boolean isCopy) {
		if (!isCopy) {
			inputFile.delete();
		}
	}

	private String makeFilePath(String outputPath, File nowFile) {
		return outputPath + File.separator + nowFile.getName();
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
