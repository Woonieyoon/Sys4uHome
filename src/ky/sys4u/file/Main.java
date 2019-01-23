package ky.sys4u.file;

import java.io.File;
import java.io.FileNotFoundException;

import ky.sys4u.file.filetree.ConvertManager;
import ky.sys4u.file.filetree.FileToFileTreeConverter;
import ky.sys4u.file.filetree.FileTree;
import ky.sys4u.file.filetree.FileTreeToStringConveter;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

//		new FileDirCopier("C:/Test1", "C:/Test2").move();
//		new DirectoryCopier("C:/before", "C:/after").move();
//		FileTree fileTree = new FileTree("C:/TreeResult/text.txt");
//		ConvertManager manager = new ConvertManager();
//		
//		//과제1
//		//manager.addConverter(FileTree.class, Boolean.class, new FileTreeToDirectoryConverter());
//		//System.out.println(manager.requestConvert(fileTree, Boolean.class));
//		
//		//과제2
//		manager.addConverter(FileTree.class, File.class, new FileTreeToFileConverter());
//		manager.addConverter(File.class, FileTree.class, new FileToFileTreeConverter());
//		

		ConvertManager manager = new ConvertManager();
		manager.addConverter(File.class, FileTree.class, new FileToFileTreeConverter("C:/TreeResult/text.txt"));
		//FileTree newFileTree = manager.requestConvert(new File("C:/TreeResult/text.txt"), FileTree.class);
		FileTree file = manager.requestConvert(new File("C:/TreeResult/text.txt"), FileTree.class);
		System.out.println(file.toString());


		new FileTreeToStringConveter().convert(file);
		
		
	}
}
