package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {
	private static final Charset UTF8 = StandardCharsets.UTF_8;
	
	private final Socket socket; 
	private final OutputStreamWriter out;
	private final BufferedReader in;
	
	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new OutputStreamWriter(socket.getOutputStream(), UTF8);
	}
	
	private static void broadcast(String message, ClientHandler self) {
		for(var client : Server.clients) {
			if(client != self) {
				try {
					client.sendMessage(message);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private final void sendMessage(String message) throws IOException {
		out.write(message);
		out.flush();
	}
	
	private final String getUsername() {
		try {
			return in.readLine();			
		} catch(IOException e) {
			return "default-username";
		}
	}
	
	@Override
	public void run() {
		try {
			out.write("\u001B[38;5;47m[Server]\u001B[0m - Enter your username: ");
			var username = getUsername();
			System.out.println("[" + username + "]: connected");
			
			var message = (String) null;
			while((message = in.readLine()) != null) {
				System.out.println("[" + username + "]: " + message);
				broadcast("\u001B[38;5;47m[" + username + "]:\u001B[0m " + message, this);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				in.close();				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
