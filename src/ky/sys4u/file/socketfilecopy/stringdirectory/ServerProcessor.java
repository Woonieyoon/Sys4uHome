package ky.sys4u.file.socketfilecopy.stringdirectory;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerProcessor {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private List<String> filelist;

	public ServerProcessor(Socket socket) {
		this.socket = socket;
		filelist = new ArrayList<>();
	}

	public void process() {

		try {

			dis = new DataInputStream((socket.getInputStream()));
			dos = new DataOutputStream(socket.getOutputStream());
			String str = dis.readUTF();

			// 파일만 남기기
			splitData(str);

			dos.writeInt(filelist.size());

			for (String file : filelist) {
				dos.writeUTF("?C" + file.substring(1));
				dos.flush();

				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));

				int bytesRead = 0;
				long size = dis.readLong();
				byte[] buffer = new byte[8192];
				while (size > 0 && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
					output.write(buffer, 0, bytesRead);
					output.flush();
					size -= bytesRead;
				}
				output.close();
			}

		} catch (Exception e) {
			// .log
		}

	}

	public void splitData(String str) {

		StringTokenizer st = new StringTokenizer(str, "\n");
		while (st.hasMoreTokens()) {
			String data = st.nextToken();
			if (data.contains("*")) {
				new File(changeString(data)).mkdirs();
			} else if (data.contains("?")) {
				String changeData = changeString(data);
				filelist.add(changeData);
			}

		}
	}

	public String changeString(String data) {
		data = data.substring(2);
		data = "E" + data;
		return data;
	}

}
