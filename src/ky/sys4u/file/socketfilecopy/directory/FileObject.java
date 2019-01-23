package ky.sys4u.file.socketfilecopy.directory;

import java.io.Serializable;

public class FileObject implements Serializable {

	private static final long serialVersionUID = 2L;

	public FileObject() {
	}

	private String originalPath;
	private String fileName;
	private byte[] fileData;
	private boolean isFile;

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

}
