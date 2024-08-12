package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
	private static final int PORT = 8080;
	public static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
	
	public static void main(String [] args) {
		try(var server = new ServerSocket(PORT)) {
			System.out.println("Server running!\n");
			
			while(true) {
				var client = server.accept();
				System.out.println("\u001B[38;5;47m[Server]\u001B[0m - " + client + "Connected");
				var cHandler = new ClientHandler(client);
				clients.add(cHandler);
				
				new Thread(cHandler).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
}
