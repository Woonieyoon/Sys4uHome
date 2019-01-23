package ky.sys4u.file.filetree;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {

	String inputPath;
	String outputPath;

	public FileManager(String inputPath, String outputPath) {
		
		if(inputPath==null || outputPath ==null)
			throw new IllegalArgumentException();
		
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	public void copyFile() {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputPath));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath))) {
			int data;
			while ((data = bis.read()) != -1) {
				bos.write(data);
			}
		} catch (IOException e) {

		}
	}

	public void moveFile() {
		copyFile();
		deleteFile(inputPath);
	}

	private void deleteFile(String inputPath) {
		File delfile = new File(inputPath);
		delfile.delete();
	}
	
	public void copyAllFile() {
		File file = new File(inputPath);
		makeAllFileRecusively(file, inputPath, outputPath);
	}

	private void makeAllFileRecusively(File file, String inPath, String outPath) {
		File[] files = file.listFiles();
		for (File nowFile : files) {
			if (nowFile.isFile()) {
				try (BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(inPath + "/" + nowFile.getName()));
						BufferedOutputStream bos = new BufferedOutputStream(
								new FileOutputStream(outPath + "/" + nowFile.getName()))) {
					int data;
					while ((data = bis.read()) != -1) {
						bos.write(data);
					}
				} catch (IOException e) {

				}
			} else if (nowFile.isDirectory()) {
				File temp = new File(outPath + "/" + nowFile.getName());
				temp.mkdir();
				makeAllFileRecusively(nowFile, inPath + "/" + nowFile.getName(), outPath + "/" + nowFile.getName());
			}
		}
	}

	public void moveAllFile() {
		File file = new File(inputPath);
		deleteAllFile(file, inputPath, outputPath);
	}

	public void deleteAllFile(File file, String inPath, String outPath) {
		File[] files = file.listFiles();
		for (File nowFile : files) {
			if (nowFile.isFile()) {
				try (BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(inPath + "/" + nowFile.getName()));
						BufferedOutputStream bos = new BufferedOutputStream(
								new FileOutputStream(outPath + "/" + nowFile.getName()))) {
					int data;
					while ((data = bis.read()) != -1) {
						bos.write(data);
					}
				} catch (IOException e) {

				}
			} else if (nowFile.isDirectory()) {
				File temp = new File(outPath + "/" + nowFile.getName());
				temp.mkdir();
				deleteAllFile(nowFile, inPath + "/" + nowFile.getName(), outPath + "/" + nowFile.getName());
			}

			nowFile.delete();
		}
	}


}
