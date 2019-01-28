package ky.sys4u.file.thread;

public class FileState {

	private int number;
	private String type;
	private String path;
	private String absolutePath;
	
	public FileState(int number,String type,String path ,String absolutePath) {
		this.number = number;
		this.type= type;
		this.path = path;
		this.absolutePath = absolutePath;
	}
	
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
