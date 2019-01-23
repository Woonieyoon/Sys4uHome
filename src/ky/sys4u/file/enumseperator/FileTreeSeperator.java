package ky.sys4u.file.enumseperator;

public enum FileTreeSeperator {

	SPACE("  "), FILESEPERATOR("└"), CRNL("\r\n");

	private String input;

	private FileTreeSeperator(String input) {
		this.input = input;
	}

	public String getSeperator() {
		return input;
	}

	public void setSeperator(String input) {
		this.input = input;
	}

}
