
package ky.sys4u.file.thread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private final String clientIP;
	private final int clientPort;
	private Socket socket;

	public Client(String clientIP, int clientPort) {

		if (clientIP == null || clientPort == 0) {
			throw new IllegalArgumentException();
		}

		this.clientIP = clientIP;
		this.clientPort = clientPort;
	}

	public void initialize() throws UnknownHostException, IOException {
		socket = new Socket(clientIP, clientPort);
	}

	public void execute(String Path) {
		ClientProcessor cp = new ClientProcessor(socket, Path);
		cp.initialize();
		cp.process();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client("localhost", 10020);
		client.initialize();
		client.execute("C:/before");



//		Client client1 = new Client("localhost", 10020);
//		client1.initialize();
//		client1.execute("C:/before");
//
//		Client client2 = new Client("localhost", 10020);
//		client2.initialize();
//		client2.execute("C:/before");
//
//		Client client3 = new Client("localhost", 10020);
//		client3.initialize();
//		client3.execute("C:/before");
//
//		Client client4 = new Client("localhost", 10020);
//		client4.initialize();
//		client4.execute("C:/before");
//
//		Client client5 = new Client("localhost", 10020);
//		client5.initialize();
//		client5.execute("C:/before");
//
//		Client client6 = new Client("localhost", 10020);
//		client6.initialize();
//		client6.execute("C:/before");
//
//		Client client7 = new Client("localhost", 10020);
//		client7.initialize();
//		client7.execute("C:/before");
//
//		Client client8 = new Client("localhost", 10020);
//		client8.initialize();
//		client8.execute("C:/before");
//
//		Client client9 = new Client("localhost", 10020);
//		client9.initialize();
//		client9.execute("C:/before");


	}
}
