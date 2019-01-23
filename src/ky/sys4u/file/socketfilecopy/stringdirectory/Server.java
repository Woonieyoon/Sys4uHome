package ky.sys4u.file.socketfilecopy.stringdirectory;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author sungw client -> server 파일,디렉토리 String 전달 디렉토리 생성 후 파일 (server) ->
 *         check(client) 파일 byte 전송(client) -> server => 파일 생성
 */
public class Server implements Closeable {

	private ServerSocket serverSocket;
	private int port;

	public Server(int port) {
		this.port = port;
	}

	public void initialize() throws Exception {
		serverSocket = new ServerSocket(port);
	}

	public void execute() throws IOException {
		while (true) {
			System.out.println("서버 접속 대기중:");
			new ServerProcessor(serverSocket.accept()).process();

		}
	}

	@Override
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		Server server = new Server(10010);
		server.initialize();
		server.execute();
		server.close();
	}

}