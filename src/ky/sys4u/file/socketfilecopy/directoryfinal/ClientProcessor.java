package ky.sys4u.file.socketfilecopy.directoryfinal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientProcessor {

	private Socket socket;
	private LeafStringCollector leafCollector;

	public ClientProcessor(Socket socket, String Path) {
		this.socket = socket;
		this.leafCollector = new LeafStringCollector(Path);
	}
	
	public void initialize() {
		leafCollector.makeData();
	}

	public void process() {
		try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());) {
			dos.writeUTF(leafCollector.getStringFileList());
			dos.flush();

			int cnt = dis.readInt();
			
			for (int i = 0; i < cnt; i++) {
				int number = dis.readInt();

				String file = fileCheck(number);
				File myFile = new File(file);
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));

				dos.writeLong(myFile.length());

				int bytesRead = 0;
				long size = myFile.length();
				byte[] buffer = new byte[8192];
				while (size > 0 && (bytesRead = bis.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
					dos.write(buffer, 0, bytesRead);
					dos.flush();
					size -= bytesRead;
				}
				bis.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("소켓종료");
	}


	public String fileCheck(int number) {
		int len = leafCollector.fileList.size();
		for (int i = 0; i < len; i++) {
			int getNumber = leafCollector.fileList.get(i).getNumber();
			if (number == getNumber) {
				return leafCollector.fileList.get(i).getAbsolutePath();
			}
		}
	return null;
	}

}
