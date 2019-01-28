package ky.sys4u.file.threadpool;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class LeafStringCollector {

	List<FileState> fileList;
	private final String path;

	public LeafStringCollector(String path) {
		this.path = path;
		fileList = new ArrayList<FileState>();
	}

	public String getStringFileList() {
		StringBuilder sb = new StringBuilder();
		for(FileState state:fileList) {
			String number = Integer.toString(state.getNumber());
			sb.append(makeDataProtocol(number,state.getType(),state.getPath()));
		}		
		return sb.toString();
	}
	
	public List<FileState> getFileList(){
		return fileList;
	}
	

	public void makeData() {
		makeFileRecursively(path);
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
				
		if(file.listFiles().length !=0) {
			return;
		}
		
		String path = makefileDirPath(file.getAbsolutePath());
		int number = fileList.size();
		fileList.add(new FileState(number, "!", path , file.getAbsolutePath()));		

	}

	public void saveFile(File file) {
		String path = makefileDirPath(file.getAbsolutePath());
		int number = fileList.size();
		fileList.add(new FileState(number, "?", path ,  file.getAbsolutePath()));		
	}
	
	public String makeDataProtocol(String number,String type,String path) {
		String result = "{0}" + ":" + "{1}" + ":" + "{2}" + ";" ;
		return MessageFormat.format(result, number , type , path);
	}
	
	public String makefileDirPath(String filePath) {
		String replacePath = filePath;
		replacePath = replacePath.replace("\\", "/");
		replacePath = replacePath.replace(path, "");
		return replacePath;		
	}

}
