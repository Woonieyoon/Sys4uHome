package ky.sys4u.file.filetree;

import static ky.sys4u.file.filetree.FileTree.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ky.sys4u.file.exception.ConversionException;

public class FileToFileTreeConverter implements Convertable<File, FileTree> {
	private static final int SPACE_LENGTH = SPACE.length();
	private String rootPath;

	public FileToFileTreeConverter(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public FileTree convert(File source) {
		List<String> dataList = new ArrayList<>();
		try {
			Files.lines(source.toPath()).sequential().forEach(dataList::add);
		} catch (IOException e) {
			throw new ConversionException(e);
		}

		return new FileTree(createRootFileNode(dataList));
	}

	private FileNode createRootFileNode(List<String> dataList) {
		FileNode rootFileNode = new FileNode(new File(rootPath));
		readLinesAndAddChildNodes(dataList, rootFileNode);
		return rootFileNode;
	}

	private void readLinesAndAddChildNodes(List<String> dataList, FileNode rootFileNode) {
		FileNode beforeNode = rootFileNode;
		for (int i = 1; i < dataList.size(); i++) {
			FileNode currentNode = addChildNode(beforeNode, dataList.get(i));
			beforeNode = currentNode;
		}
	}

	/**
	 * line을 읽어서 beforeNode로부터 얻어진 parentNode에 childNode를 add한 후 return한다.
	 * 
	 * @param beforeNode 이전에 작업한 노드
	 * @param line       파일에서 읽은 한 줄. 형식은 ..... 다.
	 * @return
	 */
	private FileNode addChildNode(FileNode beforeNode, String line) {
		int currentDepth = line.substring(0, line.indexOf(CHILD_SYMBOL)).length() / SPACE_LENGTH;//
		String currentDirName = line.substring(line.lastIndexOf(CHILD_SYMBOL) + 1);
		FileNode ancestorNode = beforeNode.getAncestor(beforeNode.getDepth() - currentDepth + 1);
		FileNode currentNode = new FileNode(new File(ancestorNode.getFile().getAbsoluteFile() + "/" + currentDirName),
				currentDepth);
		ancestorNode.addChild(currentNode);

		return currentNode;
	}
}
