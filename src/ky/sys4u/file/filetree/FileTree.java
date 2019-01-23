package ky.sys4u.file.filetree;

import java.io.File;

public class FileTree {
	public static final String CHILD_SYMBOL = "└";
	public static final String SPACE = "  ";
	private static final String CRNL = "\r\n";

	private final FileNode rootFileNode;
	private boolean initialized = false;	

	public FileTree(final String rootDirPath) {
		if (rootDirPath == null) {
			throw new IllegalArgumentException("경로�? ?���? ?��?��?��???��?��?��.");
		}
		if (!new File(rootDirPath).exists()) {
			throw new NullPointerException("존재?���? ?��?�� 경로?��?��?��.");
		}	
		this.rootFileNode = new FileNode(new File(rootDirPath));
	}

	public FileTree(final File rootDirFile) {
		if (rootDirFile == null) {
			throw new IllegalArgumentException("?��?��?�� ?���? ?��?��?��???��?��?��.");
		}
		if (!rootDirFile.exists()) {
			throw new NullPointerException("존재?���? ?��?�� ?��?��?��?��?��.");
		}
		this.rootFileNode = new FileNode(rootDirFile);
	}

	public FileTree(final FileNode rootFileNode) {
		if (rootFileNode == null) {
			throw new IllegalArgumentException("?��?�� ?��?���? ?���? ?��?��?��???��?��?��.");
		}
		this.rootFileNode = rootFileNode;
	}

	public synchronized void initialize() {
		if (initialized) {
			return;
		}
		// this.rootFileNode.removeChilderen();
		addChildrenRecursively(this.rootFileNode);
		this.initialized = true;
	}

	private void addChildrenRecursively(FileNode parentNode) {

		if (!parentNode.getChildren().isEmpty())
			return;

		File[] childrenFiles = parentNode.getFile().listFiles();
		// int depth = parentNode.getDepth();

		for (File child : childrenFiles) {
			if(child.isFile()) {
				continue;
			}

			addChildrenRecursively(parentNode.addChild(child));
		}
	}

	public FileNode getRootNode() {
		if (!initialized) {
			initialize();
		}
		return this.rootFileNode;
	}
	@Override
	public String toString() {
		StringBuilder resultStringbuilder = new StringBuilder();
		resultStringbuilder.append(this.getRootNode().getFile().getAbsolutePath() + CRNL);
		convertFileNodeToStringRecursively(this.getRootNode(), resultStringbuilder);
		return resultStringbuilder.toString();
	}
	
	private void convertFileNodeToStringRecursively(final FileNode parentNode, StringBuilder resultStringBuilder) {
		for (FileNode childNode : parentNode.getChildren()) {
			resultStringBuilder.append(getDepthSpace(childNode.getDepth())).append(childNode.getFile().getName())
					.append(CRNL);
			convertFileNodeToStringRecursively(childNode, resultStringBuilder);
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
