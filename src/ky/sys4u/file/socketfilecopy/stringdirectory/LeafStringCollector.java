package ky.sys4u.file.socketfilecopy.stringdirectory;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LeafStringCollector {

	ArrayList<FileState> fileList;
	ArrayList<String> DirectoryList;
	private final String path;
	private StringBuilder stringbuilder;

	public LeafStringCollector(String path) {
		this.path = path;
		fileList = new ArrayList<FileState>();
		DirectoryList = new ArrayList<String>();
		stringbuilder = new StringBuilder();
	}

	public String getList() {
		return stringbuilder.toString();
	}

	public void makeData() {
		makeFileRecursively(path);

		for (String str : DirectoryList) {
			stringbuilder.append(str);
		}
	}

	public void makeFileRecursively(String sourcePath) {
		File srcDir = new File(sourcePath);
		for (File file : srcDir.listFiles()) {
			if (file.isDirectory()) {
				checkLeafDirectory(file);
				makeFileRecursively(file.getAbsolutePath());
			} else {
				saveFile(file);
			}
		}
	}

	public void checkLeafDirectory(File file) {

//		if (file.listFiles().length == 0) {
//			return;
//		}

		boolean check = true;
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				check = false;
				break;
			}
		}

		if (check == false)
			return;

		String str = "*";
		str = str + file.getAbsolutePath();
		DirectoryList.add(str + "\n");

	}

	public void saveFile(File file) {
		String str = "?";
		str = "?" + file.getAbsolutePath();
		stringbuilder.append(str + "\n");

		DataInputStream diStream = null;
		try {
			FileState fileState = new FileState();
			diStream = new DataInputStream(new FileInputStream(file));
			long len = file.length();
			byte[] fileBytes = new byte[(int) len];
			diStream.read(fileBytes);
			fileState.setFileData(fileBytes);
			fileState.setOriginalPath(str);
			this.fileList.add(fileState);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
