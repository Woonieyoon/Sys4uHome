package ky.sys4u.file.filecopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ky.sys4u.file.exception.CanNotFindFile;
import ky.sys4u.file.exception.CanNotReadWriteFileException;

public class FileCopier {

	private final String inputPath;
	private final String outputPath;
	
	public FileCopier(String inputPath,String outputPath) {
		
		if(inputPath==null || outputPath==null) {
			throw new IllegalArgumentException();
		}
		
		if(new File(inputPath).isDirectory() || new File(outputPath).isDirectory()) {
			throw new CanNotFindFile();
		}
		
		this.inputPath = inputPath;
		this.outputPath = outputPath;		
	}
		

	public void copy() {
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputPath));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputPath))){
			int data;
			while((( data=bis.read())!=-1)) {
				bos.write(data);
			}			
		}catch(IOException e) {
			throw new CanNotReadWriteFileException(e);
		}	
	}


	public boolean move() {
		copy();
		return new File(inputPath).delete();
	}
	

}
