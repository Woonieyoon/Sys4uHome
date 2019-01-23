package ky.sys4u.file.socketfilecopy.directory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ky.sys4u.file.socketfilecopy.directory.exception.NetworkConnectionFail;
import ky.sys4u.file.socketfilecopy.directory.exception.NetworkInputFail;

/**
 * @author sungw
 * 직렬화를 통한 디렉토리 생성
 */
public class SocketDirectoryServer {

	private static final String SAVE_DIRECOTRY_PATH = "C:/after";

	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ObjectInputStream inputStream = null;
	private FileOutputStream fileOutputStream = null;

	public void doConnect() {
		try {
			serverSocket = new ServerSocket(10000);
			socket = serverSocket.accept();
			inputStream = new ObjectInputStream(socket.getInputStream());
			downloadFiles();
		} catch (IOException e) {
			throw new NetworkConnectionFail("네트워크 연결에 실패 했습니다.");
		}

		try {
			fileOutputStream.close();
			inputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downloadFiles() {
		try {
			FileSerialize dl = (FileSerialize) inputStream.readObject();

			int start = dl.fileObjectList.size() - 1;
			for (int i = start; i >= 0; i--) {
				FileObject fi = dl.fileObjectList.get(i);
				if (fi.isFile()) {
					File dstFile = new File(SAVE_DIRECOTRY_PATH + fi.getFileName());
					fileOutputStream = new FileOutputStream(dstFile);
					fileOutputStream.write(fi.getFileData());
					fileOutputStream.flush();
					fileOutputStream.close();
				} else {
					new File(SAVE_DIRECOTRY_PATH + fi.getFileName()).mkdirs();
				}
			}

		} catch (IOException e) {
			throw new NetworkInputFail("네트워크 Input 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SocketDirectoryServer server = new SocketDirectoryServer();
		server.doConnect();
	}
}