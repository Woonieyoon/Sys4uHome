package ky.sys4u.file.thread;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerProcessor implements Runnable {

	private final String path = "D:/before";
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private List<ServerFileCollector> filelist;

	public ServerProcessor(Socket socket) {
		this.socket = socket;
		filelist = new ArrayList<>();
	}

	@Override
	public void run() {
		process();
	}

	public void process() {
		try {

			dis = new DataInputStream((socket.getInputStream()));
			dos = new DataOutputStream(socket.getOutputStream());
			String readbeforeSplitData = dis.readUTF();

			// 파일만 남기기
			splitData(readbeforeSplitData);

			dos.writeInt(filelist.size());

			for (ServerFileCollector file : filelist) {
				dos.writeInt(file.getNumber());

				Thread t = Thread.currentThread();
				System.out.println(t.getName());

				int bytesRead = 0;
				long size = dis.readLong();

				makeDirectory(path + file.getPath());
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(path + file.getPath()));
				byte[] buffer = new byte[8192];
				while (size > 0 && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
					output.write(buffer, 0, bytesRead);
					output.flush();
					size -= bytesRead;
				}
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void splitData(String readbeforeSplitData) {
		String[] data = readbeforeSplitData.split(";");
		
		for(String filedata:data) {
			String[] splitdata = filedata.split(":");
			if(splitdata[1].equals("?")) {
				int number = Integer.parseInt(splitdata[0]);
				filelist.add(new ServerFileCollector(number, splitdata[2]));
			}else if(splitdata[1].equals("!")) {
				new File(path+splitdata[2]).mkdirs();
			}			
		}		
	}

	public void makeDirectory(String strData) {
		int number = strData.lastIndexOf("/");
		strData = strData.substring(0, number);
		new File(strData).mkdirs();
	}

}
