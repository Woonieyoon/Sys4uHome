package ky.sys4u.file.socketfilecopy.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketFileCopyClient {

	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("localhost",10002);
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dos.writeUTF("test.txt");
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:/Filebefore/test.txt"));
			
			int n;
			while((n=bis.read())!=-1) {
				dos.write(n);
			}

			dos.flush();
			socket.close();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
