package ky.sys4u.file.filetree;


public class FileTreeToStringBuilderConveter implements Convertable<FileTree, StringBuilder> {

	private static final String CRNL = "\r\n";
	private static final String SPACE = "    ";
	private static final String CHILD_SYMBOL = "?��";

	private final StringBuilder converted;

	public FileTreeToStringBuilderConveter() {
		this.converted = new StringBuilder();
	}
	
	@Override
	public StringBuilder convert(FileTree source) {
		converted.append("StringBuilderConverter : ").append(source.getRootNode().getFile().getAbsolutePath()).append(CRNL);
		convertFileNodeToStringBuilderRecursively(source.getRootNode());
		return converted;
	}
	

	private void convertFileNodeToStringBuilderRecursively(final FileNode parentNode) {
		
		for (FileNode childNode : parentNode.getChildren()) {
			converted.append(getDepthSpace(childNode.getDepth()))
				.append(childNode.getFile().getName())
				.append(CRNL);
			convertFileNodeToStringBuilderRecursively(childNode);
		}
	}

	private String getDepthSpace(final int depth) {
		StringBuilder spaceBuilder = new StringBuilder();

		for (int i = 0; i < depth; i++) {
			spaceBuilder.append(SPACE);
		}
		return spaceBuilder.append(CHILD_SYMBOL).toString();
	}



}
