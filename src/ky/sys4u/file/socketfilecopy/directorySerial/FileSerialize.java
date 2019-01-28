package ky.sys4u.file.socketfilecopy.directorySerial;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class FileSerialize implements Serializable {

	private static final long serialVersionUID = 1L;

	List<FileObject> fileObjectList;
	List<FileObject> DirectoryList;

	public FileSerialize() {
		fileObjectList = new ArrayList<FileObject>();
		DirectoryList = new ArrayList<>();
	}

	public void makeFileRecursively(String sourcePath) {
		File srcDir = new File(sourcePath);
		for (File file : srcDir.listFiles()) {
			if (file.isDirectory()) {
				makeDirectory(file, false);
				makeFileRecursively(file.getAbsolutePath());
			} else {
				makeFileObject(file, true);
			}
		}
		fileObjectList.addAll(DirectoryList);
	}

	public void makeDirectory(File file, boolean isFile) {

		boolean check = true;
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				check = false;
				break;
			}
		}

		if (check == false)
			return;

		FileObject fileObject = new FileObject();
		String path = file.getAbsolutePath();
		fileObject.setOriginalPath(path);
		fileObject.setFileName(path.substring(path.indexOf(":") + 1));
		fileObject.setFile(isFile);
		this.DirectoryList.add(fileObject);
	}

	public void makeFileObject(File file, boolean isFile) {

		FileObject fileObject = new FileObject();
		String path = file.getAbsolutePath();
		fileObject.setOriginalPath(path);
		fileObject.setFileName(path.substring(path.indexOf(":") + 1));
		fileObject.setFile(isFile);

		DataInputStream diStream = null;
		try {
			if (isFile) {
				diStream = new DataInputStream(new FileInputStream(file));
				long len = file.length();
				byte[] fileBytes = new byte[(int) len];
				diStream.read(fileBytes);
				fileObject.setFileData(fileBytes);
			}
			this.fileObjectList.add(fileObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String makeMessageFormat(String Path, String filename) {
		String result = "{0}" + File.separator + "{1}";
		return MessageFormat.format(result, Path, filename);
	}

}
