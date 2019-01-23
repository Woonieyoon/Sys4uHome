package ky.sys4u.file.socketfilecopy.stringdirectorytwo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	private Socket socket;
	private final int serverPort;
	private FileOutputStream fos;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	public Server(int serverPort) {
		this.serverPort = serverPort;
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doConnect() {
		try {
			socket = serverSocket.accept();

			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());

			int count = 1;
			boolean isFile = false;
			String filePath = "D:/after";
			while (true) {

				byte[] b = new byte[8192];

				switch (count) {
				case 1:
					System.out.println("들어왔어요");

					int a;
					bis.read(b);
					filePath = new String(b, "euc-kr");

					// 파일
					if (filePath.contains("@")) {
						isFile = true;
					} else if (filePath.contains("!")) {
						isFile = false;
					}
					// filePath = "D" + filePath.substring(2);
					System.out.println("서버" + filePath.trim());
					String str = "more";
					bos.write(str.getBytes());
					bos.flush();

					break;
				case 2:
					if (isFile) {
						System.out.println("서버 두번째");
						int n;
						
						// File file = new File(filePath);
						// BufferedOutputStream bos = new BufferedOutputStream(new
						// FileOutputStream(file));
						// bis.read();
						int data;
						while ((data = bis.read()) != -1) {
							// bos.write(data);
							// System.out.println("데이터쓰는중");
						}
						// bos.wr

						System.out.println("두번쨰 값은:" + b);
					}else {
						System.out.println("파일경로:" + filePath);
						File file = new File(filePath);
						file.mkdirs();
					}
					System.out.println("----------------------");

					break;

				}

				count++;
				if (count == 3) {
					count = 1;
				}


			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Server server = new Server(10002);
		server.doConnect();
	}

}