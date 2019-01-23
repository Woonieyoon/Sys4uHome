
package ky.sys4u.file.socketfilecopy.stringdirectorytwo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

	private final String clientIP;
	private final int clientPort;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	private List<byte[]> allData;
	private List<byte[]> leafDirList;

	public Client(String clientIP, int clientPort) {

		if (clientIP == null || clientPort == 0) {
			throw new IllegalArgumentException();
		}
		
		this.clientIP = clientIP;
		this.clientPort = clientPort;
		allData = new ArrayList<>();
		leafDirList = new ArrayList<>();


	}

	public void doConnect() {

		try {
			socket = new Socket(clientIP, clientPort);
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			// makeFileRecursively(sourcePath);
			sendData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void makeData(String path) {
		makeFileRecursively(path);
		allData.addAll(leafDirList);
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

		boolean check = true;
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				check = false;
				break;
			}
		}

		if (check == false)
			return;

		String str = "!";
		str = str + file.getAbsolutePath();
		leafDirList.add(str.getBytes());
		byte[] b = null;
		leafDirList.add(b);
	}

	public void saveFile(File file) {
		String str = "@";
		str = "@" + file.getAbsolutePath();
		allData.add(str.getBytes());

		ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
		byte[] buf = new byte[8192];

		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {

			int n;
			byte[] b = new byte[1024];
			while ((n = bis.read(b)) != -1) {
				baos.write(buf, 0, n);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		allData.add(baos.toByteArray());
	}

	public void sendData() {

		int cnt = allData.size() - 1;

		while (cnt >= 0) {
			try {

				System.out.println("데이터전송");
				byte b[] = allData.get(cnt - 1);
				dos.write(b);
				dos.flush();

				byte[] b1 = new byte[30];
				dis.read(b1);

				String str = new String(b); // 디렉토리구분
				String rec = new String(b1); // more더 달라고 햇는지
				if (rec.trim().equals("more") && (!str.contains("!"))) {
					// System.out.println("사이즈 버퍼" + allData.get(cnt));
					dos.write(allData.get(cnt));
					dos.flush();
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			cnt = cnt - 2;
		}

	}

	public void Check() {
		System.out.println(allData.size() + "");
	}

	public static void main(String[] args) {
		Client client = new Client("localhost", 10002);
		client.makeData("C:/before");
		client.doConnect();
	}

}

