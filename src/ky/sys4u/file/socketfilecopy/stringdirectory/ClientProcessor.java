package ky.sys4u.file.socketfilecopy.stringdirectory;

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

	public ClientProcessor(Socket socket, LeafStringCollector leafCollector) {
		this.socket = socket;
		this.leafCollector = leafCollector;
	}

	public void process() {
		try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());) {
			System.out.println("쓴다");
			dos.writeUTF(leafCollector.getList());
			dos.flush();

			int cnt = dis.readInt();

			for (int i = 0; i < cnt; i++) {
				String recData = dis.readUTF();

				String file = fileCheck(recData);
				file = file.substring(1);

				File myFile = new File(file);
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));

				// byte[] databyte = new byte[(int) myFile.length()];
				dos.writeLong(myFile.length());

				int bytesRead = 0;
				// long size = databyte.length;
				long size = myFile.length();
				byte[] buffer = new byte[8192];
				while (size > 0 && (bytesRead = bis.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
					dos.write(buffer, 0, bytesRead);
					dos.flush();
					size -= bytesRead;
				}
				System.out.println("크기" + file);
				System.out.println("데이터서버로 전송");
				bis.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("소켓종료");
	}


	public String fileCheck(String recData) {
		int len = leafCollector.fileList.size();
		System.out.println("검색해야할 데이터" + recData);
		for (int i = 0; i < len; i++) {
			String savedData = leafCollector.fileList.get(i).getOriginalPath();
			System.out.println(savedData);
			if (recData.equals(savedData)) {
				System.out.println("찾았다.");
				return savedData;
			}
		}
		return null;
	}

}
