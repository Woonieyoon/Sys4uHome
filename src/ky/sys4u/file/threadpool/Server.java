package ky.sys4u.file.threadpool;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sungw client -> server 파일,디렉토리 String 전달 디렉토리 생성 후 파일 (server) ->
 *         check(client) 파일 byte 전송(client) -> server => 파일 생성
 */
public class Server implements Closeable {

	private ServerSocket serverSocket;
	private int port;
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public Server(int port) {
		this.port = port;
	}

	public void initialize() throws Exception {
		serverSocket = new ServerSocket(port);
	}

	public void execute() throws IOException {
		while (true) {
			System.out.println("클라이언트 대기중 !!");
			Socket socket = serverSocket.accept();
			//new Thread(new ServerProcessor(socket)).start();
			threadPool.execute(new ServerProcessor(socket));
			System.out.println("클라이언트 종료 !!");
		}
	}

	@Override
	public void close() {
		try {
			serverSocket.close();
			threadPool.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(10020);
		server.initialize();
		server.execute();
		server.close();
	}

}