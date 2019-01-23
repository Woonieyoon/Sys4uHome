package ky.sys4u.file.filetree;

public class FileTreeToStringConveter implements Convertable<FileTree,String> {

	private String converted;

	public FileTreeToStringConveter() {
		this.converted = "";
	}
	
	@Override
	public String convert(final FileTree source) {
		converted += "StringConverter : "+source.toString();
		return converted;
	}
}
