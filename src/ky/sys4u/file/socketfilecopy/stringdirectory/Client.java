
package ky.sys4u.file.socketfilecopy.stringdirectory;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private final String clientIP;
	private final int clientPort;
	private Socket socket;
	private LeafStringCollector leafCollector;

	public Client(String clientIP, int clientPort, String path) {

		if (clientIP == null || clientPort == 0) {
			throw new IllegalArgumentException();
		}

		this.clientIP = clientIP;
		this.clientPort = clientPort;
		leafCollector = new LeafStringCollector(path);
	}

	public void initialize() throws UnknownHostException, IOException {
		socket = new Socket(clientIP, clientPort);
	}

	public void makeLeaf() {
		leafCollector.makeData();
	}

	public void execute() {
		new ClientProcessor(socket, leafCollector).process();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost", 10010, "C:/before");
		client.makeLeaf();
		client.initialize();
		client.execute();
	}
}
