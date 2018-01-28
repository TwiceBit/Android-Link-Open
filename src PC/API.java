import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class API extends Thread {
	private int port = 8888;

	public API(int port) {

		this.port = port;

	}

	@Override
	public void run() {

		try {

			ServerSocket server = new ServerSocket(port);
			Socket client = null;
			while (server.isBound()) {
				client = server.accept();
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					while (client.isConnected()) {
						if (in.ready()) {
							String txt = in.readLine();
							if (txt != null || !txt.isEmpty()) {
								ArrayList<String> list = new ArrayList<>();
								list.add("firefox");
								list.add(txt);
								new ProcessBuilder(list).start();
								client.close();
								break;
							}
						}
					}

				} catch (Exception e) {
				}

			}

		} catch (Exception e) {

		}

	}
}
