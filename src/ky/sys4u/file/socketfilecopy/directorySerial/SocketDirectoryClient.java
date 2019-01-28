package ky.sys4u.file.socketfilecopy.directorySerial;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ky.sys4u.file.socketfilecopy.directory.exception.NetworkConnectionFail;
import ky.sys4u.file.socketfilecopy.directory.exception.NetworkOutputFail;

public class SocketDirectoryClient {

	private final String filePath;
	private Socket socket = null;
	private ObjectOutputStream outputStream = null;
	private FileSerialize datalist;
	private boolean isConnected = false;

	public SocketDirectoryClient(String filePath) {

		if (filePath == null) {
			throw new IllegalArgumentException();
		}

		datalist = new FileSerialize();
		this.filePath = filePath;
	}

	public void connect() {
		if (!isConnected) {
			try {
				socket = new Socket("localhost", 10000);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				isConnected = true;
			} catch (IOException e) {
				throw new NetworkConnectionFail("네트워크 연결에 실패 했습니다.");
			}
		}
	}

	public void Send() {

		datalist.makeFileRecursively(filePath);

		try {
			outputStream.writeObject(datalist);
			outputStream.flush();
			System.out.println("SSS");
			Thread.sleep(10000);
		} catch (IOException e) {
			throw new NetworkOutputFail("네트워크 OutPut 실패");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SocketDirectoryClient client = new SocketDirectoryClient("C:/before");
		client.connect();
		client.Send();
	}
}