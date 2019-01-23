package ky.sys4u.file.socketfilecopy.stringdirectory;

public class FileState {

	private byte[] fileData;
	private String originalPath;
	
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getOriginalPath() {
		return originalPath;
	}
	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
}
