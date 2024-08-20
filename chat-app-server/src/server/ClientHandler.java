package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

	private final Socket client;
	private final OutputStreamWriter out;
	private final BufferedReader in;

	public ClientHandler(Socket client) throws IOException {
		this.client = client;
		this.out = new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8);
		this.in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
	}

	private static final void broadcast(String message, ClientHandler sender) {
		for (var client : Server.clients) {
			if (client != sender) {
				try {
					client.sendMessage(message);
				} catch (IOException e) { // TODO Tem forma melhor para isto
					e.printStackTrace();
				}
			}
		}
	}

	private final void sendMessage(String message) throws IOException {
		out.write(message);
		out.flush();
	}

	@Override
	public void run() {
		try {
			var username = "tester";
			System.out.println("[" + username + "]: Connected.");

			var inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				System.out.println("[" + username + "]: " + inputLine);
				broadcast(inputLine, this);
			}
			
			Server.clients.remove(this);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			Server.clients.remove(this);
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
