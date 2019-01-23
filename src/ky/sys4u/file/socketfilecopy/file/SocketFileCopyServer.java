package ky.sys4u.file.socketfilecopy.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketFileCopyServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(10002);
			Socket socket = server.accept();		
			String path = "C:/Fileafter/";
		
			DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));			
			path += dis.readUTF();
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
			
			int data;
			while((data=dis.read()) != -1) {
				bos.write(data);
			}
			
			bos.flush();
			server.close();
			socket.close();
			dis.close();
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

