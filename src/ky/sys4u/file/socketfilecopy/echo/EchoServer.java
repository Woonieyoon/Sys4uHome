package ky.sys4u.file.socketfilecopy.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(11111);
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			System.out.println("서버 대기중....");
			while (true) {
				String msg = br.readLine();
				if (msg.equalsIgnoreCase("Quit"))
					break;
				System.out.println("클라이언트에서 보냄:[ " + msg + " ]");
				out.println(msg);
				out.flush();
			}
			System.out.println("서버종료..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
